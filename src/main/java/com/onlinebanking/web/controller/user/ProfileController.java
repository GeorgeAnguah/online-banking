package com.onlinebanking.web.controller.user;

import com.onlinebanking.annotation.Loggable;
import com.onlinebanking.backend.service.UserService;
import com.onlinebanking.constant.HomeConstants;
import com.onlinebanking.constant.SystemConstants;
import com.onlinebanking.constant.user.ProfileConstants;
import com.onlinebanking.constant.user.UserConstants;
import com.onlinebanking.enums.UserHistoryType;
import com.onlinebanking.shared.dto.UserDto;
import com.onlinebanking.shared.util.SecurityUtils;
import com.onlinebanking.shared.util.UserUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Objects;

/**
 * This controller handles all requests from the browser relating to user profile.
 *
 * @author Eric Opoku
 * @version 1.0
 * @since 1.0
 */
@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping(ProfileConstants.PROFILE_MAPPING)
@PreAuthorize("isAuthenticated() and hasAnyRole(T(com.onlinebanking.enums.RoleType).values())")
public class ProfileController {

    private final UserService userService;

    /**
     * View user's page.
     *
     * @param model              The model to convey objects to view layer
     * @param redirectAttributes the redirectAttribute
     *
     * @return profile page.
     */
    @Loggable
    @GetMapping
    public String profile(final Principal principal, final Model model, RedirectAttributes redirectAttributes) {
        UserDto storedUserDto = userService.findByUsername(principal.getName());
        if (Objects.isNull(storedUserDto)) {
            redirectAttributes.addFlashAttribute(SystemConstants.ERROR, true);
            return HomeConstants.REDIRECT_TO_LOGIN;
        }
        model.addAttribute(UserConstants.USER_MODEL_KEY, storedUserDto);
        return ProfileConstants.USER_PROFILE_VIEW_NAME;
    }

    /**
     * Updates the user profile with the details provided.
     *
     * @param user   the user
     * @param result the binding result
     * @param model  the model with redirection
     *
     * @return the view to profile page.
     */
    @Loggable
    @PreAuthorize("isFullyAuthenticated()")
    @PutMapping(ProfileConstants.PROFILE_UPDATE_MAPPING)
    public String updateProfile(@Valid @ModelAttribute UserDto user, BindingResult result, RedirectAttributes model) {
        var userDetails = SecurityUtils.getAuthenticatedUserDetails();
        if (result.hasErrors() || Objects.isNull(userDetails) || !user.getEmail().equals(userDetails.getEmail())) {
            model.addFlashAttribute(SystemConstants.ERROR, true);
            return HomeConstants.REDIRECT_TO_LOGIN;
        }
        UserUtils.enableUser(user);
        user.setId(userDetails.getId());
        userService.updateUser(user, UserHistoryType.PROFILE_UPDATE);

        // Authenticate user with the updated profile.
        SecurityUtils.authenticateUser(userService, user.getUsername());
        return ProfileConstants.REDIRECT_TO_PROFILE;
    }

}
