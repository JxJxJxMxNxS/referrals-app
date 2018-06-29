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

    @RequestMapping(value = "/sendEmail" , method=RequestMethod.POST)
    public String sendMail(@RequestParam("recruiter_id") Long recruiter_id,@RequestParam("job_id") Long job_id,
                           @RequestParam("name") String name,@RequestParam("email") String email) {
        mailerService.sendEmail(recruiter_id,job_id,name,email);
        return "Email sent";
    }
}