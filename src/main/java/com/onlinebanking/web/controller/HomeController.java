package com.onlinebanking.web.controller;

import com.onlinebanking.constant.HomeConstants;
import com.onlinebanking.shared.util.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * HomeController class managers all incoming request to the home page.
 *
 * @author George on 6/19/2021
 * @version 1.0
 * @since 1.0
 */
@Controller
@RequestMapping(HomeConstants.INDEX_URL_MAPPING)
public class HomeController {

    /**
     * Maps index url request.
     *
     * @return index view name
     */
    @GetMapping
    public String home() {
        // if the user is authenticated, redirect to the account overview.
        if (SecurityUtils.isUserAuthenticated()) {
            return HomeConstants.REDIRECT_TO_ACCOUNT_OVERVIEW;
        }

        return HomeConstants.INDEX_VIEW_NAME;
    }
}
