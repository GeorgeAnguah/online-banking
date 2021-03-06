package com.onlinebanking.backend.service.account.impl;

import com.onlinebanking.backend.persistent.domain.User;
import com.onlinebanking.backend.persistent.domain.account.CheckingAccount;
import com.onlinebanking.backend.persistent.domain.account.SavingsAccount;
import com.onlinebanking.backend.persistent.repository.CheckingAccountRepository;
import com.onlinebanking.backend.persistent.repository.SavingsAccountRepository;
import com.onlinebanking.backend.persistent.repository.UserRepository;
import com.onlinebanking.backend.service.account.AccountService;
import com.onlinebanking.constant.AccountConstants;
import com.onlinebanking.constant.CacheConstants;
import com.onlinebanking.shared.util.AccountUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.security.Principal;

/**
 * Implementation of the AccountService used for account business logic.
 *
 * @author Matthew Puentes on 8/31/2021
 * @version 1.0
 * @since 1.0
 */
@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
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
    @Transactional
    public CheckingAccount createCheckingAccount() {
        CheckingAccount checkingAccount = new CheckingAccount();
        checkingAccount.setBalance(BigDecimal.ZERO);
        checkingAccount.setAccountNumber(AccountUtils.accountNumberGenerator());
        return checkingAccountRepository.save(checkingAccount);
    }

    /**
     * Create a savings account.
     *
     * @return SavingsAccount that's created.
     */
    @Override
    @Transactional
    public SavingsAccount createSavingsAccount() {
        SavingsAccount savingsAccount = new SavingsAccount();
        savingsAccount.setBalance(BigDecimal.ZERO);
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
    @Transactional
    @Caching(evict = @CacheEvict(value = CacheConstants.USERS, key = "#principal.name"))
    public void deposit(String accountType, String amount, Principal principal) {
        User user = userRepository.findByUsername(principal.getName());

        if (accountType.equalsIgnoreCase(AccountConstants.CHECKING_ACCOUNT)) {
            var checkingAccount = user.getCheckingAccount();
            checkingAccount.setBalance(checkingAccount.getBalance().add(new BigDecimal(amount)));
            checkingAccountRepository.saveAndFlush(checkingAccount);
        } else if (accountType.equalsIgnoreCase(AccountConstants.SAVINGS_ACCOUNT)) {
            var savingsAccount = user.getSavingsAccount();
            savingsAccount.setBalance(savingsAccount.getBalance().add(new BigDecimal(amount)));
            savingsAccountRepository.saveAndFlush(savingsAccount);
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
    @Transactional
    @Caching(evict = @CacheEvict(value = CacheConstants.USERS, key = "#principal.name"))
    public void withdraw(String accountType, String amount, Principal principal) {
        User user = userRepository.findByUsername(principal.getName());

        if (accountType.equalsIgnoreCase(AccountConstants.CHECKING_ACCOUNT)) {
            var checkingAccount = user.getCheckingAccount();
            checkingAccount.setBalance(checkingAccount.getBalance().subtract(new BigDecimal(amount)));
            checkingAccountRepository.saveAndFlush(checkingAccount);
        } else if (accountType.equalsIgnoreCase(AccountConstants.SAVINGS_ACCOUNT)) {
            var savingsAccount = user.getSavingsAccount();
            savingsAccount.setBalance(savingsAccount.getBalance().subtract(new BigDecimal(amount)));
            savingsAccountRepository.saveAndFlush(savingsAccount);
        }
    }


}
