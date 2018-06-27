package com.Nearsoft.referrals.service.impl;

import com.Nearsoft.referrals.model.Job;
import com.Nearsoft.referrals.repository.GitHubJobRepository;
import com.Nearsoft.referrals.repository.JobRepository;
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

        jobs.stream().forEach(job -> jobRepository.save(job));
    }
}
