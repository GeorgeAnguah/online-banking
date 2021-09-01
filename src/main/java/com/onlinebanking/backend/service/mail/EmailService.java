package com.onlinebanking.backend.service.mail;

import com.onlinebanking.backend.pojo.BaseEmail;

/**
 * Email service interface used to send emails.
 */
public interface EmailService {
    /**
     * Send a simple email.
     *
     * @param email email to send
     */
    void sendEmail(BaseEmail email);
}
