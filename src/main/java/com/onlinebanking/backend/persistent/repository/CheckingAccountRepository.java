package com.onlinebanking.backend.persistent.repository;

import com.onlinebanking.backend.persistent.domain.account.CheckingAccount;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CheckingAccountRepository extends CrudRepository<CheckingAccount, Long> {
    CheckingAccount findByAccountNumber(int accountNumber);
}
