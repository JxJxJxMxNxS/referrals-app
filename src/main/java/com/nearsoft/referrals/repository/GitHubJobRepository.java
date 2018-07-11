package com.nearsoft.referrals.repository;

import com.nearsoft.referrals.model.Job;

import java.util.List;

public interface GitHubJobRepository {
    List<Job> retrieveJobs();
}
