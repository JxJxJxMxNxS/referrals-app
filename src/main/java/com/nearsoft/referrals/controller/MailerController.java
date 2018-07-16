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
import java.util.MissingFormatArgumentException;
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
                                   @RequestParam("strong_referral_quantity_time") Optional<Integer> strongReferralQuantityTime, @RequestParam("strong_referral_ago") Optional<String> strongReferralago, @RequestParam("strong_referral_where") Optional<String> strongReferralWhere,
                                   @RequestParam("strong_referral_why") Optional<String> strongReferralWhy) throws MessagingException, IOException {
        String fileName = storageService.store(file);
        ReferBody referBody = new ReferBody();
        referBody.setJob_id(jobId);
        referBody.setRecruiter_id(recruiterId);
        referBody.setReferred_name(referredName);
        referBody.setReferred_email(referredEmail);
        referBody.setResume_file(file);

        strongReferral.ifPresent(isStrong -> {
            if (!isStrong)
                return;
            referBody.setStrong_referral(isStrong);
            referBody.setStrong_referral_quantity_time(strongReferralQuantityTime.orElseThrow(() -> new MissingFormatArgumentException("strong_referral_quantity_time should be present when the parameter string_referral is true")));
            referBody.setStrong_referral_ago(strongReferralago.orElseThrow(() -> new MissingFormatArgumentException("strong_referral_ago should be present when the parameter string_referral is true")));
            referBody.setStrong_referral_where(strongReferralWhere.orElseThrow(() -> new MissingFormatArgumentException("strong_referral_where should be present when the parameter string_referral is true")));
            referBody.setStrong_referral_why(strongReferralWhy.orElseThrow(() -> new MissingFormatArgumentException("strong_referral_why should be present when the parameter string_referral is true")));
        });
        mailerService.sendEmail(referBody, fileName);


        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
}