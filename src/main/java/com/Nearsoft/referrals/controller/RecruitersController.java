package com.Nearsoft.referrals.controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;
import com.Nearsoft.referrals.service.RecruiterService;
import com.Nearsoft.referrals.model.Recruiter;;
@RestController
public class RecruitersController{
    
    private RecruiterService recruiterService;
    public RecruitersController(RecruiterService recruiterService){
        this.recruiterService = recruiterService;
    }

    @RequestMapping(value="/recruiters")
    public List<Recruiter> getJobs(){
        return recruiterService.getRecruiters();
    }
}