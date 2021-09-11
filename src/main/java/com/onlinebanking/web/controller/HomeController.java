package com.onlinebanking.web.controller;

import com.onlinebanking.backend.persistent.domain.User;
import com.onlinebanking.backend.persistent.domain.account.CheckingAccount;
import com.onlinebanking.backend.persistent.domain.account.SavingsAccount;
import com.onlinebanking.backend.service.UserService;
import com.onlinebanking.constant.AccountConstants;
import com.onlinebanking.constant.HomeConstants;
import com.onlinebanking.shared.util.SecurityUtils;
import com.onlinebanking.shared.util.UserUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

/**
 * HomeController class managers all incoming request to the home page.
 *
 * @author George on 6/19/2021
 * @version 1.0
 * @since 1.0
 */
@Controller
@RequiredArgsConstructor
@RequestMapping(HomeConstants.INDEX_URL_MAPPING)
public class HomeController {
    private final UserService userService;

    /**
     * Maps index url request.
     *
     * @return index view name
     */
    @GetMapping
    public String home() {
        // if the user is authenticated, redirect to the account overview.
        if (SecurityUtils.isAuthenticated()) {
            return HomeConstants.REDIRECT_TO_ACCOUNT_OVERVIEW;
        }

        return HomeConstants.INDEX_VIEW_NAME;
    }

    /**
     * Maps account overview url request with appropriate attributes.
     *
     * @param principal principal to retrieve current logged in user.
     * @param model model to map appropriate attributes.
     * @return account overview name.
     */
    @PreAuthorize("isAuthenticated() and hasAnyRole(T(com.onlinebanking.enums.RoleType).values())")
    @GetMapping(HomeConstants.ACCOUNT_OVERVIEW_URL_MAPPING)
    public String accountOverview(Principal principal, Model model) {
        User user = UserUtils.convertToUser(userService.findByUsername(principal.getName()));
        CheckingAccount checkingAccount = user.getCheckingAccount();
        SavingsAccount savingsAccount = user.getSavingsAccount();

        model.addAttribute(AccountConstants.CHECKING_ACCOUNT_MODEL_KEY, checkingAccount);
        model.addAttribute(AccountConstants.SAVINGS_ACCOUNT_MODEL_KEY, savingsAccount);

        return HomeConstants.ACCOUNT_OVERVIEW_NAME;
    }
}
