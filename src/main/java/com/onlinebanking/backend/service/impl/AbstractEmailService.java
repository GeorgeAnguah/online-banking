package com.onlinebanking.backend.service.impl;

import com.onlinebanking.backend.pojo.BaseEmail;
import com.onlinebanking.backend.service.mail.EmailService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.List;
import java.util.Objects;

/**
 * This class abstracts the common functionality for
 * sending email. Subclasses must override the abstract send method
 * to meet their need.
 *
 * @author George on 8/27/2021
 * @version 1.0
 * @since 1.0
 */
@Slf4j
@RequiredArgsConstructor
public abstract class AbstractEmailService implements EmailService {
    @Getter
    private final JavaMailSenderImpl mailSender;

    @Override
    public void sendEmail(BaseEmail email) {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        try {
            helper.setFrom(email.getFrom());
            helper.setTo(email.getTo());
            helper.setSubject(email.getSubject());
            helper.setText(email.getContents(), true);

            // add recipients if any to this email
            List<String> recipients = email.getRecipients();
            if (Objects.nonNull(recipients)) {
                helper.setBcc(recipients.toArray(new String[0]));
            }
            send(message);          // send email message
        } catch (MessagingException e) {
            LOG.info("email error : {}", e.getMessage());
        }

    }

    /**
     * send email options.
     * Subclasses must override to send either a mock or actual email.
     *
     * @param message message containing email content.
     */
    public abstract void send(MimeMessage message);
}
