package com.nearsoft.referrals.service;

import com.nearsoft.referrals.model.Job;
import com.nearsoft.referrals.model.MostReferredJob;

import java.util.List;

public interface JobService {
    List<Job> getJobs();

    List<MostReferredJob> getMostReferredJob();
}