package com.Nearsoft.referrals.controller;

import com.Nearsoft.referrals.service.MailerService;
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
    public String sendMail(@RequestParam("recruiter_id") Long recruiter_id,@RequestParam("job_id") Long job_id,
                           @RequestParam("referred_name") String referred_name,@RequestParam("referred_email") String referred_email) {

        mailerService.sendEmail(recruiter_id,job_id,referred_name,referred_email);
        return "Email sent";
    }
}