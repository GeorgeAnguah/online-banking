package com.onlinebanking.backend.service.impl;

import com.onlinebanking.backend.service.mail.EmailConfig;
import com.onlinebanking.constant.ProfileTypeConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;

/**
 * This class performs the actual email sending functionality using
 * the JavaMailSender.
 *
 * @author George on 8/13/2021
 * @version 1.0
 * @since 1.0
 */
@Slf4j
@Service
@Profile(ProfileTypeConstants.PROD)
public class OnlineBankingEmailServiceImpl extends AbstractEmailService {

    /**
     * Creates the OnlineBankingEmailServiceImpl instance.
     *
     * @param mailSender  mailSender that sends email.
     * @param emailConfig emailConfig holds email properties.
     */
    public OnlineBankingEmailServiceImpl(JavaMailSenderImpl mailSender, EmailConfig emailConfig) {
        super(mailSender, emailConfig);
    }

    @Override
    public void send(MimeMessage message) {
        try {
            getMailSender().send(message);
            LOG.info("Mock email message sent : {}", message.getContent());
        } catch (IOException | MessagingException e) {
            LOG.error("OnlineBankingEmailServiceImpl error : " + e.getMessage());
        }
    }

}
