package com.Nearsoft.referrals.service;

import javax.mail.MessagingException;
import java.io.IOException;

public interface MailerService {
    void sendEmail(Long recruiterId, Long jobId, String referredName, String referredEmail, String fileName) throws MessagingException, IOException;
}
