package com.onlinebanking.backend.service.mail;

import lombok.Getter;
import lombok.Setter;

/**
 * Email container used in EmailService.
 *
 * @author Matthew Puentes on 7/19/2021
 * @version 1.0
 * @since 1.0
 */
@Getter
@Setter
public class Email {
    private String to;
    private String from;
    private String subject;
    private String content;
}
