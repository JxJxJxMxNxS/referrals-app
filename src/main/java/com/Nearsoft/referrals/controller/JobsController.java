package com.Nearsoft.referrals.controller;

import com.Nearsoft.referrals.model.Job;
import com.Nearsoft.referrals.service.JobService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
public class JobsController{

    private JobService jobService;

    public JobsController(JobService jobService) {
        this.jobService = jobService;
    }

    @RequestMapping(value="/jobs")
    public List<Job> getJobs(){
        return jobService.getJobs();
    }
}