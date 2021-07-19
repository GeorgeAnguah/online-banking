package com.onlinebanking.backend.service.mail;

import com.onlinebanking.backend.pojo.Email;

/**
 * Email service interface used to send emails.
 */
public interface EmailService {
    /**
     * Send a simple email.
     *
     * @param email email to send
     */
    void sendSimpleEmail(Email email);
}
