package com.Nearsoft.referrals.controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;
import com.Nearsoft.referrals.service.JobService;
import com.Nearsoft.referrals.model.Job;
@RestController
public class JobsController{
    
    private JobService jobService;
    public JobsController(JobService jobService){
        this.jobService = jobService;
    }

    @RequestMapping(value="/jobs")
    public List<Job> getJobs(){
        return jobService.getJobs();
    }
}