package com.onlinebanking.web.controller;

import com.onlinebanking.annotation.Loggable;
import com.onlinebanking.backend.service.account.AccountService;
import com.onlinebanking.constant.AccountConstants;
import com.onlinebanking.constant.HomeConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

/**
 * AccountController class manages all incoming request to the account page.
 *
 * @author Matthew Puentes on 9/7/2021
 * @version 1.0
 * @since 1.0
 */
@Controller
@RequiredArgsConstructor
@RequestMapping(AccountConstants.ACCOUNT_URL_MAPPING)
public class AccountController {
    private final AccountService accountService;

    /**
     * Maps to deposit page.
     *
     * @param model model to supply attributes to deposit page
     * @return deposit page
     */
    @GetMapping("/deposit")
    @Loggable
    public String deposit(Model model) {
        model.addAttribute("accountType","");
        model.addAttribute("amount","");

        return "deposit";
    }

    /**
     * Deposit money into account.
     *
     * @param amount amount to be deposited
     * @param accountType accountType to deposit money from
     * @param principal principal to retrieve current logged in user
     * @return account overview page
     */
    @PostMapping("/deposit")
    @Loggable
    public String depositIntoAccount(@ModelAttribute("amount") String amount,
                                     @ModelAttribute("accountType") String accountType,
                                     Principal principal) {
        accountService.deposit(accountType, amount, principal);

        return HomeConstants.REDIRECT_TO_ACCOUNT_OVERVIEW;
    }

    /**
     * Maps to withdraw page.
     *
     * @param model model to supply attributes to withdraw page
     * @return withdraw page
     */
    @GetMapping("/withdraw")
    @Loggable
    public String withdraw(Model model) {
        model.addAttribute("accountType","");
        model.addAttribute("amount","");

        return "withdraw";
    }

    /**
     * Withdraw money from account.
     *
     * @param amount amount to withdraw
     * @param accountType accountType to withdraw from
     * @param principal principal to retrieve current logged in user
     * @return account overview page
     */
    @PostMapping("/withdraw")
    @Loggable
    public String withdrawFromAccount(@ModelAttribute("amount") String amount,
                                     @ModelAttribute("accountType") String accountType,
                                     Principal principal) {
        accountService.withdraw(accountType, amount, principal);

        return HomeConstants.REDIRECT_TO_ACCOUNT_OVERVIEW;
    }
}
