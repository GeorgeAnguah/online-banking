package com.onlinebanking.backend.service.account;

import com.onlinebanking.backend.persistent.domain.account.CheckingAccount;
import com.onlinebanking.backend.persistent.domain.account.SavingsAccount;

import java.security.Principal;

/**
 * This AccountService interface is the contract for the account service operations.
 *
 * @author Matthew Puentes on 8/24/2021
 * @version 1.0
 * @since 1.0
 */
public interface AccountService {
    /**
     * Create a checking account.
     *
     * @return CheckingAccount that's created.
     */
    CheckingAccount createCheckingAccount();

    /**
     * Create a savings account.
     *
     * @return SavingsAccount that's created.
     */
    SavingsAccount createSavingsAccount();

    /**
     * Deposit money into bank account.
     *
     * @param accountType accountType that specifies a savings or checking account.
     * @param amount amount to be deposited.
     * @param principal principal for currently logged in user.
     */
    void deposit(String accountType, String amount, Principal principal);

    /**
     * Withdraw money from bank account.
     *
     * @param accountType accountType that specifies a savings or checking account.
     * @param amount amount to be withdrawn.
     * @param principal principal for currently logged in user.
     */
    void withdraw(String accountType, String amount, Principal principal);
}
