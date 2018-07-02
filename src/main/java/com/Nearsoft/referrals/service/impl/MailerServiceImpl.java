package com.Nearsoft.referrals.service.impl;

import com.Nearsoft.referrals.service.MailerService;
import com.Nearsoft.referrals.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.IOException;

@Service
public class MailerServiceImpl implements MailerService {

    @Autowired
    private JavaMailSender emailSender;

    @Async
    @Override
    public void sendEmail(Long recruiterId, Long jobId, String referredName, String referredEmail, String fileName, StorageService storageService) throws MessagingException, IOException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo("pon_900@hotmail.com");
        helper.setSubject("Referred to an opening position");
        helper.setText("Hello " + recruiterId + ", \n " + referredName + " has been referred for the " + jobId + " position, \n Email: " + referredEmail);
        if (fileName != null) {
            FileSystemResource file
                    = new FileSystemResource(new File("./" + fileName));
            helper.addAttachment(fileName, file);
        }
        emailSender.send(message);
        storageService.deleteFile();
    }

}
