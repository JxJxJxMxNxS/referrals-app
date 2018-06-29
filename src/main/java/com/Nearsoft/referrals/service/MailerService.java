package com.Nearsoft.referrals.service;

public interface MailerService {
    void sendEmail(Long recruiter_id,Long job_id,String name, String email);
}
