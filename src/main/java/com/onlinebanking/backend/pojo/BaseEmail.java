package com.onlinebanking.backend.pojo;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Email used for email service.
 *
 * @author Matthew Puentes on 7/19/2021
 * @version 1.0
 * @since 1.0
 */
@Data
@Builder
public class BaseEmail {
    private final String to;
    private final String from;
    private final String subject;

    private List<String> recipients;
    private Map<String, String> urls;

    public List<String> getRecipients() {
        return new ArrayList<>(recipients);
    }

    public void setRecipients(List<String> recipients) {
        this.recipients = new ArrayList<>(recipients);
    }

    public Map<String, String> getUrls() {
        return new HashMap<>(urls);
    }

    public void setUrls(Map<String, String> urls) {
        this.urls = new HashMap<>(urls);
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BaseEmail)) {
            return false;
        }
        BaseEmail baseEmail = (BaseEmail) o;
        return Objects.equals(getTo(), baseEmail.getTo())
               && Objects.equals(getFrom(), baseEmail.getFrom())
               && Objects.equals(getSubject(), baseEmail.getSubject());
    }

    @Override
    public final int hashCode() {
        return Objects.hash(getTo(), getFrom(), getSubject());
    }
}
