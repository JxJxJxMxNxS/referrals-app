package com.Nearsoft.referrals.service;

public interface MailerService {
    void sendEmail(Long recruiterId, Long jobId, String referredName, String referredEmail);
}
