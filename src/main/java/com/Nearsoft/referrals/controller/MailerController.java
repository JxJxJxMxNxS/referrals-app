package com.Nearsoft.referrals.controller;

import com.Nearsoft.referrals.service.MailerService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MailerController {
    private MailerService mailerService;

    public MailerController(MailerService mailerService) {
        this.mailerService = mailerService;
    }

    @RequestMapping(value = "/sendEmail")
    public String sendMail() {
        mailerService.sendEmail();
        return "Email sent";
    }
}
