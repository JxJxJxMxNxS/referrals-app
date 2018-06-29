package com.Nearsoft.referrals.service;

public interface MailerService {
    void sendEmail(Long recruiter_id,Long job_id,String referred_name, String referred_email);
}
