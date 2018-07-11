package com.nearsoft.referrals.controller;

import com.nearsoft.referrals.model.Recruiter;
import com.nearsoft.referrals.service.RecruiterService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class RecruitersController {

    private RecruiterService recruiterService;

    public RecruitersController(RecruiterService recruiterService) {
        this.recruiterService = recruiterService;
    }

    @RequestMapping(value = "/recruiters")
    public List<Recruiter> getRecruiters() {
        return recruiterService.getRecruiters();
    }
}