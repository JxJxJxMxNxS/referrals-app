package com.Nearsoft.referrals.controller;

import com.Nearsoft.referrals.service.MailerService;
import com.Nearsoft.referrals.service.StorageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import java.io.IOException;


@RestController
public class MailerController {
    private MailerService mailerService;
    private StorageService storageService;

    public MailerController(MailerService mailerService, StorageService storageService) {
        this.mailerService = mailerService;
        this.storageService = storageService;

    }

    @RequestMapping(value = "/refer", method = RequestMethod.POST)
    public ResponseEntity sendMail(@RequestParam(value = "resume_file", required = false) MultipartFile file, @RequestParam("recruiter_id") Long recruiterId, @RequestParam("job_id") Long jobId,

                                   @RequestParam("referred_name") String referredName, @RequestParam("referred_email") String referredEmail) throws MessagingException, IOException {
        String fileName = storageService.store(file);

        mailerService.sendEmail(recruiterId, jobId, referredName, referredEmail, fileName);


        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
}