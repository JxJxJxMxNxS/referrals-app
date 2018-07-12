package com.nearsoft.referrals.service;

import com.nearsoft.referrals.model.ReferBody;

import javax.mail.MessagingException;
import java.io.IOException;

public interface MailerService {
    void sendEmail(ReferBody referBody, String fileName) throws MessagingException, IOException;
}
