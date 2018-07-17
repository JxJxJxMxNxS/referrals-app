package com.nearsoft.referrals.controller;

import com.nearsoft.referrals.model.Company;
import com.nearsoft.referrals.model.ReferBody;
import com.nearsoft.referrals.service.CompanyService;
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
    private CompanyService companyService;

    public MailerController(MailerService mailerService, StorageService storageService, CompanyService companyService) {
        this.mailerService = mailerService;
        this.storageService = storageService;
        this.companyService = companyService;
    }

    @RequestMapping(value = "/refer", method = RequestMethod.POST)
    public ResponseEntity sendMail(@RequestParam(value = "resume_file", required = false) MultipartFile file, @RequestParam("recruiter_id") Long recruiterId, @RequestParam("job_id") Long jobId,
                                   @RequestParam("referred_name") String referredName, @RequestParam("referred_email") String referredEmail, @RequestParam("strong_referral") Optional<Boolean> strongReferral,
                                   @RequestParam("strong_referral_year") Optional<Integer> strongReferralYear, @RequestParam("strong_referral_month") Optional<Integer> strongReferralMonth, @RequestParam("strong_referral_where") Optional<String> strongReferralWhere,
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
            referBody.setStrong_referral_year(strongReferralYear.orElseThrow(() -> new MissingFormatArgumentException("strong_referral_year should be present when the parameter string_referral is true")));
            referBody.setStrong_referral_month(strongReferralMonth.orElseThrow(() -> new MissingFormatArgumentException("strong_referral_month should be present when the parameter string_referral is true")));
            referBody.setStrong_referral_where(strongReferralWhere.orElseThrow(() -> new MissingFormatArgumentException("strong_referral_where should be present when the parameter string_referral is true")));
            referBody.setStrong_referral_why(strongReferralWhy.orElseThrow(() -> new MissingFormatArgumentException("strong_referral_why should be present when the parameter string_referral is true")));
            Company company = new Company();
            company.setName(referBody.getStrong_referral_where());
            companyService.storeCompanyIfNotExists(company);
        });


        mailerService.sendEmail(referBody, fileName);


        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
}