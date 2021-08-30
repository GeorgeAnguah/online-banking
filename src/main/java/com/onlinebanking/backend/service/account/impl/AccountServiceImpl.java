package com.onlinebanking.backend.service.account.impl;

import com.onlinebanking.backend.persistent.domain.User;
import com.onlinebanking.backend.persistent.domain.account.CheckingAccount;
import com.onlinebanking.backend.persistent.domain.account.SavingsAccount;
import com.onlinebanking.backend.persistent.repository.CheckingAccountRepository;
import com.onlinebanking.backend.persistent.repository.SavingsAccountRepository;
import com.onlinebanking.backend.persistent.repository.UserRepository;
import com.onlinebanking.backend.service.account.AccountService;
import com.onlinebanking.shared.util.AccountUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.security.Principal;

@RequiredArgsConstructor
@Service
public class AccountServiceImpl implements AccountService {
    private final CheckingAccountRepository checkingAccountRepository;
    private final SavingsAccountRepository savingsAccountRepository;
    private final UserRepository userRepository;

    /**
     * Create a checking account.
     *
     * @return CheckingAccount that's created.
     */
    @Override
    public CheckingAccount createCheckingAccount() {
        CheckingAccount checkingAccount = new CheckingAccount();
        checkingAccount.setCheckingBalance(new BigDecimal("0.0"));
        checkingAccount.setAccountNumber(AccountUtils.accountNumberGenerator());
        return checkingAccountRepository.save(checkingAccount);
    }

    /**
     * Create a savings account.
     *
     * @return SavingsAccount that's created.
     */
    @Override
    public SavingsAccount createSavingsAccount() {
        SavingsAccount savingsAccount = new SavingsAccount();
        savingsAccount.setSavingsBalance(new BigDecimal("0.0"));
        savingsAccount.setAccountNumber(AccountUtils.accountNumberGenerator());
        return savingsAccountRepository.save(savingsAccount);
    }

    /**
     * Deposit money into bank account.
     *
     * @param accountType accountType that specifies a savings or checking account.
     * @param amount      amount to be deposited.
     * @param principal   principal for currently logged in user.
     */
    @Override
    public void deposit(String accountType, Double amount, Principal principal) {
        User user = userRepository.findByUsername(principal.getName());

        //make enum for checking and saving account
        if(accountType.equalsIgnoreCase("CheckingAccount")) {
            CheckingAccount checkingAccount = user.getCheckingAccount();
            checkingAccount.setCheckingBalance(checkingAccount.getCheckingBalance().add(new BigDecimal(amount)));
            checkingAccountRepository.save(checkingAccount);
        }
        else if(accountType.equalsIgnoreCase("SavingsAccount")) {
            SavingsAccount savingsAccount = user.getSavingsAccount();
            savingsAccount.setSavingsBalance(savingsAccount.getSavingsBalance().add(new BigDecimal(amount)));
            savingsAccountRepository.save(savingsAccount);
        }
    }

    /**
     * Withdraw money from bank account.
     *
     * @param accountType accountType that specifies a savings or checking account.
     * @param amount      amount to be withdrawn.
     * @param principal   principal for currently logged in user.
     */
    @Override
    public void withdraw(String accountType, Double amount, Principal principal) {
    }
}
