package com.nearsoft.referrals.controller;

import com.nearsoft.referrals.model.Company;
import com.nearsoft.referrals.model.ReferBody;
import com.nearsoft.referrals.model.User;
import com.nearsoft.referrals.service.*;
import freemarker.template.TemplateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import java.io.IOException;
import java.security.Principal;
import java.util.MissingFormatArgumentException;
import java.util.Optional;


@RestController
public class MailerController {
    private MailerService mailerService;
    private StorageService storageService;
    private CompanyService companyService;
    private ReferredByUserService referredByUserService;
    private UserService userService;
    private static final Logger LOGGER = LoggerFactory.getLogger(MailerController.class);

    public MailerController(MailerService mailerService, StorageService storageService, CompanyService companyService, ReferredByUserService referredByUserService, UserService userService) {
        this.mailerService = mailerService;
        this.storageService = storageService;
        this.companyService = companyService;
        this.referredByUserService = referredByUserService;
        this.userService = userService;
    }

    @RequestMapping(value = "/refer", method = RequestMethod.POST)
    public ResponseEntity sendMail(@RequestParam(value = "resume_file", required = false) MultipartFile file, @RequestParam("recruiter_id") Long recruiterId, @RequestParam("job_id") Long jobId,
                                   @RequestParam("referred_name") String referredName, @RequestParam("referred_email") String referredEmail, @RequestParam("strong_referral") Optional<Boolean> strongReferral,
                                   @RequestParam("strong_referral_year") Optional<Integer> strongReferralYear, @RequestParam("strong_referral_month") Optional<Integer> strongReferralMonth, @RequestParam("strong_referral_where") Optional<String> strongReferralWhere,
                                   @RequestParam("strong_referral_why") Optional<String> strongReferralWhy, Principal principal) throws MessagingException, IOException, TemplateException {
        String fileName = storageService.store(file);
        ReferBody referBody = new ReferBody();
        referBody.setJob_id(jobId);
        referBody.setRecruiter_id(recruiterId);
        referBody.setReferred_name(referredName);
        referBody.setReferred_email(referredEmail);
        referBody.setResume_file(file);

        User user = userService.getPrincipalUser(principal.getName());
        referBody.setUser_id(user.getId());

        LOGGER.trace("Referring a person with this data: {}", referBody);
        strongReferral.ifPresent(isStrong -> {
            LOGGER.trace("The referred was a strong referral");
            if (!isStrong)
                return;
            referBody.setStrong_referral(isStrong);
            referBody.setStrong_referral_year(strongReferralYear.orElseThrow(() -> new MissingFormatArgumentException("strong_referral_year should be present when the parameter string_referral is true")));
            referBody.setStrong_referral_month(strongReferralMonth.orElseThrow(() -> new MissingFormatArgumentException("strong_referral_month should be present when the parameter string_referral is true")));
            referBody.setStrong_referral_where(strongReferralWhere.orElseThrow(() -> new MissingFormatArgumentException("strong_referral_where should be present when the parameter string_referral is true")));
            referBody.setStrong_referral_why(strongReferralWhy.orElseThrow(() -> new MissingFormatArgumentException("strong_referral_why should be present when the parameter string_referral is true")));


            Company company = new Company();
            company.setName(referBody.getStrong_referral_where());
            LOGGER.trace("Trying to store the company: {}", company);
            companyService.storeCompanyIfNotExists(company);
            LOGGER.trace("Company : {} successfully stored", company);
            LOGGER.info("The user: {} stored the company: {}", user, company);
        });


        LOGGER.trace("Trying to store the referred: {}", referBody);
        referredByUserService.storeReferred(referBody);
        LOGGER.trace("Successfully sotred the referred: {}", referBody);

        LOGGER.trace("Trying to send the email to the recruiter");
        mailerService.sendEmail(referBody, fileName);
        LOGGER.trace("Email successfully sent");
        LOGGER.info("The user: {} referred {}:", user, referBody);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
}