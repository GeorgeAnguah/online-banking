package com.onlinebanking.backend.service.account;

import com.onlinebanking.backend.persistent.domain.account.CheckingAccount;
import com.onlinebanking.backend.persistent.domain.account.SavingsAccount;
import com.onlinebanking.backend.persistent.repository.CheckingAccountRepository;
import com.onlinebanking.backend.persistent.repository.SavingsAccountRepository;
import com.onlinebanking.backend.persistent.repository.UserRepository;
import com.onlinebanking.backend.service.account.impl.AccountServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.security.Principal;


class AccountServiceTest {

    private int accountNumber;
    private CheckingAccount checkingAccount;
    private SavingsAccount savingsAccount;
    private final Principal principal = Mockito.mock(Principal.class);
    private Double testAmount;

    //When using service interface, test will fail because no implementation
    @InjectMocks
    AccountServiceImpl accountServiceImpl;

    @Mock
    CheckingAccountRepository checkingAccountRepository;

    @Mock
    SavingsAccountRepository savingsAccountRepository;

    @Mock
    UserRepository userRepository;

    /**
     * TODO: create three captors for deposit arguments.
     */
    @Captor
    private ArgumentCaptor<String> accountTypeCaptor;

    @Captor
    private ArgumentCaptor<Double> amountCaptor;

    @Captor
    private ArgumentCaptor<Principal> principalCaptor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        accountNumber = 11443333;
        testAmount = 10.00;
        checkingAccount = new CheckingAccount();
        checkingAccount.setAccountNumber(accountNumber);

        savingsAccount = new SavingsAccount();
        savingsAccount.setAccountNumber(accountNumber);
    }

    @Test
    void createCheckingAccount() {
        Mockito.when(checkingAccountRepository.save(ArgumentMatchers.any(CheckingAccount.class))).thenReturn(checkingAccount);
        Assertions.assertAll(() -> {
            Assertions.assertNotNull(checkingAccount);
            Assertions.assertEquals(accountNumber, checkingAccount.getAccountNumber());
            Assertions.assertEquals(checkingAccount, accountServiceImpl.createCheckingAccount());
        });
    }

    @Test
    void createSavingsAccount() {
        Mockito.when(savingsAccountRepository.save(ArgumentMatchers.any(SavingsAccount.class))).thenReturn(savingsAccount);
        Assertions.assertAll(() -> {
            Assertions.assertNotNull(savingsAccount);
            Assertions.assertEquals(savingsAccount.getAccountNumber(), accountNumber);
            Assertions.assertEquals(savingsAccount, accountServiceImpl.createSavingsAccount());
        });
    }

    @Test
    void depositMoneyIntoCheckingAccount() {
//        var accountType = "CheckingAccount";
//        var amount = 10.00;
//        var username = principal.getName();
//        user.setCheckingAccount(checkingAccount);
//        Mockito.when(principal.getName()).thenReturn(user.getUsername());
//        Mockito.when(userRepository.findByUsername(username)).thenReturn(user);
//        Mockito.when(checkingAccountRepository.save(ArgumentMatchers.any(CheckingAccount.class))).thenReturn(checkingAccount);
//
//
//
//        Assertions.assertAll(() -> {
//            Assertions.assertEquals("CheckingAccount", accountType);
//            Assertions.assertEquals(user.getUsername(), principal.getName());
//            Assertions.assertEquals(user, userRepository.findByUsername(username));
//            Assertions.assertNotNull(user.getCheckingAccount());
//        });
//
//        Mockito.verify(accountService, Mockito.times(1)).deposit(accountType, amount, principal);

    }

    //add mockito verify to deposit method or create separate test?
    @Test
    public void whenCheckingDepositCalledValueCaptured() {
        AccountServiceImpl accountTest = Mockito.mock(AccountServiceImpl.class);
        ArgumentCaptor<String> valueCapture = ArgumentCaptor.forClass(String.class);
        Mockito.doNothing().when(accountTest).deposit(valueCapture.capture(),
                ArgumentMatchers.any(Double.class),  ArgumentMatchers.any(Principal.class));
        accountTest.deposit("CheckingAccount", testAmount, principal);
        Assertions.assertEquals("CheckingAccount", valueCapture.getValue());
    }

    @Test
    public void whenSavingsDepositCalledValueCaptured() {
        AccountServiceImpl accountTest = Mockito.mock(AccountServiceImpl.class);
        Mockito.doNothing().when(accountTest).deposit(accountTypeCaptor.capture(),
                amountCaptor.capture(),  principalCaptor.capture());
        accountTest.deposit("SavingsAccount", testAmount, principal);
        //Assertions lambda
    }
}