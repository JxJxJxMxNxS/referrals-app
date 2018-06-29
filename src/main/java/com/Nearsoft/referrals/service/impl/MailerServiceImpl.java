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
    public void sendEmail() {
        String to, subject, text;
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("pon_900@hotmail.com");
        message.setSubject("Prueba");
        message.setText("K pez");
        emailSender.send(message);
    }

}
