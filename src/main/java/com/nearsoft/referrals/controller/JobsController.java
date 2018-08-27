package com.nearsoft.referrals.controller;

import com.nearsoft.referrals.model.Job;
import com.nearsoft.referrals.service.JobService;
import com.nearsoft.referrals.service.impl.JobsUpdaterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class JobsController {

    private JobService jobService;
    private JobsUpdaterService jobsUpdaterService;
    private static final Logger LOGGER = LoggerFactory.getLogger(JobsController.class);

    public JobsController(JobService jobService, JobsUpdaterService jobsUpdaterService) {
        this.jobService = jobService;
        this.jobsUpdaterService = jobsUpdaterService;
    }

    @RequestMapping(value = "/jobs")
    public List<Job> getJobs() {
        LOGGER.trace("Retrieving jobs");
        List<Job> jobs = jobService.getJobs();
        LOGGER.trace("Retrieved jobs: {}", jobs);
        return jobs;
    }

    @RequestMapping(value = "/update_jobs", method = RequestMethod.POST)
    public void updateJobs() {
        LOGGER.trace("Updating jobs");
        jobsUpdaterService.updateLocalRepository();
        LOGGER.info("Jobs successfully updated");
    }


}