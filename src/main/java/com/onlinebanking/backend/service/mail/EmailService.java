package com.onlinebanking.backend.service.mail;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * This class will send email in real time as well as test emails
 * for testing purposes.
 *
 * @author Matthew Puentes on 7/19/2021
 * @version 1.0
 * @since 1.0
 */
@RequiredArgsConstructor
@Service
public class EmailService {
    private final JavaMailSender javaMailSender;

    /**
     * Used to send a simple email.
     *
     * @param email email to send.
     */
    public void sendSimpleEmail(Email email) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(email.getTo());
        simpleMailMessage.setFrom(email.getFrom());
        simpleMailMessage.setSubject(email.getSubject());
        javaMailSender.send(simpleMailMessage);
    }
}
