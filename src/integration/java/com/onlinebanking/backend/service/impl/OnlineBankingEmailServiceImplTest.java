package com.onlinebanking.backend.service.impl;

import com.onlinebanking.backend.pojo.BaseEmail;
import com.onlinebanking.backend.service.mail.EmailConfig;
import com.onlinebanking.backend.service.mail.EmailService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

/**
 * Test production EmailService implementation.
 *
 * @author George on 8/26/2021
 * @version 1.0
 * @since 1.0
 */
@SpringBootTest
class OnlineBankingEmailServiceImplTest {
    @Autowired
    private EmailService emailService;

    @Autowired
    private EmailConfig emailConfig;

    private BaseEmail email;

    @BeforeEach
    void setUp() {
        email = BaseEmail.builder()
                .from(emailConfig.getUsername())
                .to(emailConfig.getUsername())
                .subject("Test email sending")
                .contents("Congratulation, test email sending").build();
    }

    @Test
    void sendEmailToUserWithoutLinkOrRecipients() {
        Assertions.assertAll(() -> {
            Assertions.assertNotNull(emailService);
            Assertions.assertNotNull(emailConfig);
            Assertions.assertDoesNotThrow(() -> emailService.sendEmail(email));
        });
    }

    @Test
    void sendEmailToUserWithLink() {
        Map<String, String> links = new HashMap<>();
        String url = "https://spring.io/projects/spring-boot";
        links.put("spring", url);
        email.setUrls(links);
        email.setContents("Congratulation, email sent with link : " + links.get("spring"));
        Assertions.assertDoesNotThrow(() -> emailService.sendEmail(email));
    }
}