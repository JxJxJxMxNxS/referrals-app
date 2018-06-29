package com.Nearsoft.referrals.controller;

import com.Nearsoft.referrals.service.MailerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MailerController {
    private MailerService mailerService;

    public MailerController(MailerService mailerService) {
        this.mailerService = mailerService;
    }

    @RequestMapping(value = "/refer" , method=RequestMethod.POST)
    public ResponseEntity sendMail(@RequestParam("recruiter_id") Long recruiterId, @RequestParam("job_id") Long jobId,
                                   @RequestParam("referred_name") String referredName, @RequestParam("referred_email") String referredEmail) {

        mailerService.sendEmail(recruiterId, jobId, referredName, referredEmail);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
}