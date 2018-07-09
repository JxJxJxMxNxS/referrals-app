package com.Nearsoft.referrals.repository;

import com.Nearsoft.referrals.model.Job;

import java.util.List;

public interface GitHubJobRepository {
    List<Job> retrieveJobs();
}
