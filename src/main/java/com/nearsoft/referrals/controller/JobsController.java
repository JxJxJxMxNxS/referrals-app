package com.nearsoft.referrals.controller;

import com.nearsoft.referrals.model.Job;
import com.nearsoft.referrals.service.JobService;
import com.nearsoft.referrals.service.impl.JobsUpdaterService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class JobsController {

    private JobService jobService;
    private JobsUpdaterService jobsUpdaterService;


    public JobsController(JobService jobService, JobsUpdaterService jobsUpdaterService) {
        this.jobService = jobService;
        this.jobsUpdaterService = jobsUpdaterService;
    }

    @RequestMapping(value = "/jobs")
    public List<Job> getJobs() {
        return jobService.getJobs();
    }

    @RequestMapping(value = "/update_jobs", method = RequestMethod.POST)
    public void updateJobs() {
        jobsUpdaterService.updateLocalRepository();
    }

}