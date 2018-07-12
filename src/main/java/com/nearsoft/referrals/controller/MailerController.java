package com.nearsoft.referrals.controller;

import com.nearsoft.referrals.model.ReferBody;
import com.nearsoft.referrals.service.MailerService;
import com.nearsoft.referrals.service.StorageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.Optional;


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
                                   @RequestParam("referred_name") String referredName, @RequestParam("referred_email") String referredEmail, @RequestParam("strong_referral") Optional<Boolean> strongReferral,
                                   @RequestParam("strong_referral_quantity_time") Optional<Integer> storngReferralQuantityTime, @RequestParam("strong_referral_ago") Optional<String> storngReferralago, @RequestParam("strong_referral_where") Optional<String> storngReferralWhere,
                                   @RequestParam("strong_referral_why") Optional<String> storngReferralWhy) throws MessagingException, IOException {
        String fileName = storageService.store(file);
        ReferBody referBody = new ReferBody();
        referBody.setJob_id(jobId);
        referBody.setRecruiter_id(recruiterId);
        referBody.setReferred_name(referredName);
        referBody.setReferred_email(referredEmail);
        referBody.setResume_file(file);

        strongReferral.ifPresent(isStrong -> {
            referBody.setStrong_referral(isStrong);
            referBody.setStrong_referral_quantity_time(storngReferralQuantityTime.get());
            referBody.setStrong_referral_ago(storngReferralago.get());
            referBody.setStrong_referral_where(storngReferralWhere.get());
            referBody.setStrong_referral_why(storngReferralWhy.get());
        });
        mailerService.sendEmail(referBody, fileName);


        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
}