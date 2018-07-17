package com.nearsoft.referrals.service.impl;

import com.nearsoft.referrals.model.Job;
import com.nearsoft.referrals.repository.GitHubJobRepository;
import com.nearsoft.referrals.repository.JobRepository;
import com.nearsoft.referrals.service.JobService;
import org.springframework.stereotype.Service;

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

        return jobRepository.findAll();
    }


}