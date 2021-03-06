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
 * Test MockEmailService.
 *
 * @author George on 8/13/2021
 * @version 1.0
 * @since 1.0
 */
@SpringBootTest
class MockEmailServiceImplTest {
    @Autowired
    private EmailService emailService;

    @Autowired
    private EmailConfig emailConfig;

    private BaseEmail email;

    @BeforeEach
    void setUp() {
        email = BaseEmail.builder()
                .from("developBoard@gmail.com")
                .to("User@gmail.com")
                .contents("MockEmailService email test")
                .subject("Testing email").build();
    }

    @Test
    void testMockEmailServiceSendEmail() {
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