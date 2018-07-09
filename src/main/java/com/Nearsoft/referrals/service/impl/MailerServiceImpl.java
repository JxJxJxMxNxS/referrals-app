package com.Nearsoft.referrals.service.impl;

import com.Nearsoft.referrals.model.Job;
import com.Nearsoft.referrals.model.Recruiter;
import com.Nearsoft.referrals.repository.JobRepository;
import com.Nearsoft.referrals.repository.RecruiterRepository;
import com.Nearsoft.referrals.service.JobService;
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
import java.io.IOException;

@Service
public class MailerServiceImpl implements MailerService {

    @Autowired
    private JavaMailSender emailSender;
    @Autowired
    private StorageService storageService;
    @Autowired
    private RecruiterRepository recruiterRepository;
    @Autowired
    private JobService jobService;
    @Autowired
    private JobRepository jobRepository;

    @Async
    @Override
    public void sendEmail(Long recruiterId, Long jobId, String referredName, String referredEmail, String fileName) throws MessagingException, IOException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        Recruiter recruiter;
        Job job;
        recruiter = recruiterRepository.findById(recruiterId).get();
        job = jobRepository.findById(jobId).get();


        helper.setTo(recruiter.getEmail());
        helper.setSubject("Referred to an opening position");
        helper.setText("Hello " + recruiter.getName() + ", \n " + referredName + " has been referred for the " + job.getTitle() + " position, \n Email: " + referredEmail);

        if (fileName != null) {
            FileSystemResource file = storageService.getFileSystemResource(fileName);
            helper.addAttachment(fileName, file);
        }
        emailSender.send(message);
        storageService.deleteFile(fileName);
    }

}
