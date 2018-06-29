package com.Nearsoft.referrals.service.impl;

import com.Nearsoft.referrals.service.MailerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailerServiceImpl implements MailerService {

    @Autowired
    public JavaMailSender emailSender;

    @Override
    public void sendEmail(Long recruiter_id,Long job_id,String name, String email) {
        String to, subject, text;
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("pon_900@hotmail.com");
        message.setSubject("Referred to an opening position");
        message.setText("Hello "+recruiter_id+", \n "+name+" has been referred for the "+job_id+" position, \n Email: "+email);
        emailSender.send(message);
    }

}
