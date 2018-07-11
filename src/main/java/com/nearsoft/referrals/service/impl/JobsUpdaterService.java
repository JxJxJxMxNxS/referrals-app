package com.nearsoft.referrals.service.impl;

import com.nearsoft.referrals.model.Job;
import com.nearsoft.referrals.repository.GitHubJobRepository;
import com.nearsoft.referrals.repository.JobRepository;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class JobsUpdaterService {
    private GitHubJobRepository gitHubJobRepository;

    private JobRepository jobRepository;

    public JobsUpdaterService(GitHubJobRepository gitHubJobRepository, JobRepository jobRepository) {
        this.gitHubJobRepository = gitHubJobRepository;
        this.jobRepository = jobRepository;
    }

    @PostConstruct
    public void updateLocalRepository() {
        List<Job> jobs = gitHubJobRepository.retrieveJobs();

        jobs.forEach(job -> jobRepository.save(job));
    }
}
