package com.onlinebanking.backend.service.mail.impl;

import com.onlinebanking.backend.service.mail.AbstractEmailService;
import com.onlinebanking.constant.ProfileTypeConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;

/**
 * This class mocks the email sending service.
 * Meaning, the send function simply logs the email message content.
 * This is to help simulate email service during the development phase.
 *
 * @author George on 8/13/2021
 * @version 1.0
 * @since 1.0
 */
@Slf4j
@Service
@Profile({ProfileTypeConstants.TEST, ProfileTypeConstants.DEV})
public class MockEmailServiceImpl extends AbstractEmailService {

    /**
     * Creates the MockEmailServiceImpl instance.
     *
     * @param mailSender mailSender that sends email.
     */
    public MockEmailServiceImpl(JavaMailSenderImpl mailSender) {
        super(mailSender);
    }

    @Override
    public void send(MimeMessage message) {
        try {
            LOG.info("Mock email message sent : {}", message.getContent());
        } catch (IOException | MessagingException e) {
            LOG.error("MockEmailService error : " + e.getMessage());
        }
    }

}
