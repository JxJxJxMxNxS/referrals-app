package com.Nearsoft.referrals.controller;

import com.Nearsoft.referrals.model.Recruiter;
import com.Nearsoft.referrals.service.RecruiterService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

;
@RestController
public class RecruitersController{
    
    private RecruiterService recruiterService;
    public RecruitersController(RecruiterService recruiterService){
        this.recruiterService = recruiterService;
    }

    @RequestMapping(value="/recruiters")
    public List<Recruiter> getRecruiters() {
        return recruiterService.getRecruiters();
    }
}