package com.Nearsoft.referrals.service.impl;

import com.Nearsoft.referrals.service.MailerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class MailerServiceImpl implements MailerService {

    @Autowired
    private JavaMailSender emailSender;

    @Async
    @Override
    public void sendEmail(Long recruiterId, Long jobId, String referredName, String referredEmail) {
        String to, subject, text;
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("pon_900@hotmail.com");
        message.setSubject("Referred to an opening position");
        message.setText("Hello " + recruiterId + ", \n " + referredName + " has been referred for the " + jobId + " position, \n Email: " + referredEmail);
        emailSender.send(message);
    }

}
