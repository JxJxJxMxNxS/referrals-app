package com.nearsoft.referrals.service.impl;

import com.nearsoft.referrals.model.Job;
import com.nearsoft.referrals.model.Recruiter;
import com.nearsoft.referrals.model.ReferBody;
import com.nearsoft.referrals.repository.JobRepository;
import com.nearsoft.referrals.repository.RecruiterRepository;
import com.nearsoft.referrals.service.JobService;
import com.nearsoft.referrals.service.MailerService;
import com.nearsoft.referrals.service.StorageService;
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
    public void sendEmail(ReferBody referBody, String fileName) throws MessagingException, IOException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        String bodyMessage;

        Recruiter recruiter;
        Job job;
        recruiter = recruiterRepository.findById(referBody.getRecruiter_id()).get();
        job = jobRepository.findById(referBody.getJob_id()).get();

        bodyMessage = "Hello " + recruiter.getName() + ", \n" + referBody.getReferred_name() + " has been referred for the " + job.getTitle() +
                " position, \nEmail: " + referBody.getReferred_email();
        if (referBody.getStrong_referral()) {
            bodyMessage = bodyMessage + "\nWhen: " + referBody.getStrong_referral_month() + "/" + referBody.getStrong_referral_year() + "\n" +
                    "Where: " + referBody.getStrong_referral_where() + "\n" +
                    "Why: " + referBody.getStrong_referral_why();
        }
        helper.setTo(recruiter.getEmail());
        helper.setSubject(referBody.getStrong_referral() ? "Strong referal to an opening position" : "Referred to an opening position");
        helper.setText(bodyMessage);

        if (fileName != null) {
            FileSystemResource file = storageService.getFileSystemResource(fileName);
            helper.addAttachment(fileName, file);
        }
        emailSender.send(message);
        storageService.deleteFile(fileName);
    }

}
