package com.Nearsoft.referrals.service.impl;

import com.Nearsoft.referrals.model.Job;
import com.Nearsoft.referrals.repository.GitHubJobRepository;
import com.Nearsoft.referrals.repository.JobRepository;
import com.Nearsoft.referrals.service.JobService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JobServiceImpl implements JobService {

    private GitHubJobRepository gitHubJobRepository;

    private JobRepository jobRepository;

    public JobServiceImpl(GitHubJobRepository gitHubJobRepository, JobRepository jobRepository) {
        this.gitHubJobRepository = gitHubJobRepository;
        this.jobRepository = jobRepository;
    }

    @Override
    public List<Job> getJobs() {
        List<Job> jobs;
        jobs = gitHubJobRepository.retrieveJobs();

        return jobs;
    }


}