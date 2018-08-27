package com.nearsoft.referrals.controller;

import com.nearsoft.referrals.model.Recruiter;
import com.nearsoft.referrals.service.RecruiterService;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class RecruitersController {

    private RecruiterService recruiterService;
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(RecruitersController.class);

    public RecruitersController(RecruiterService recruiterService) {
        this.recruiterService = recruiterService;
    }

    @RequestMapping(value = "/recruiters")
    public List<Recruiter> getRecruiters() {
        LOGGER.trace("Retrieving the recruiters");
        List<Recruiter> recruiters = recruiterService.getRecruiters();
        LOGGER.trace("Recruiters retrieved: {}", recruiters);
        return recruiters;
    }
}