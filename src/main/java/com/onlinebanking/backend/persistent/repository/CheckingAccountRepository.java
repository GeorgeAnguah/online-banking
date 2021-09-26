package com.onlinebanking.backend.persistent.repository;

import com.onlinebanking.backend.persistent.domain.account.CheckingAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for the CheckingAccount.
 *
 * @author Matthew Puentes on 8/31/2021
 * @version 1.0
 * @since 1.0
 */
@Repository
public interface CheckingAccountRepository extends JpaRepository<CheckingAccount, Long> {
    /**
     * Find checking account by checking account number.
     *
     * @param accountNumber accountNumber used to find checking account.
     * @return Retrieved checking account.
     */
    CheckingAccount findByAccountNumber(int accountNumber);
}
