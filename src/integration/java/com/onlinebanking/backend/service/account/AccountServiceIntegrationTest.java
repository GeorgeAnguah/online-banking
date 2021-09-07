package com.onlinebanking.backend.service.account;

import com.onlinebanking.IntegrationTestUtils;
import com.onlinebanking.backend.service.UserService;
import com.onlinebanking.shared.util.UserUtils;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;

public class AccountServiceIntegrationTest extends IntegrationTestUtils {

    @Autowired
    AccountService accountService;

    @Autowired
    UserService userService;

    private final MockHttpServletRequest request = new MockHttpServletRequest();

    void depositIntoCheckingAccount() {
        var user = UserUtils.createUser();
        //need username from principal object

        Assertions.assertNotNull(user);

    }
}
