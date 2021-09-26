package com.onlinebanking.backend.persistent.repository;

import com.onlinebanking.backend.persistent.domain.account.SavingsAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for the SavingsAccount.
 *
 * @author Matthew Puentes on 8/31/2021
 * @version 1.0
 * @since 1.0
 */
@Repository
public interface SavingsAccountRepository extends JpaRepository<SavingsAccount, Long> {
    /**
     * Find savings account by checking account number.
     *
     * @param accountNumber accountNumber used to find savings account.
     * @return Retrieved savings account.
     */
    SavingsAccount findByAccountNumber(int accountNumber);
}
