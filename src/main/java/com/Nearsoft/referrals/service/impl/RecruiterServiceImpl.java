package com.Nearsoft.referrals.service.impl;

import com.Nearsoft.referrals.model.Recruiter;
import com.Nearsoft.referrals.repository.RecruiterRepository;
import com.Nearsoft.referrals.service.RecruiterService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecruiterServiceImpl implements RecruiterService {

    private RecruiterRepository recruiterRepository;

    public RecruiterServiceImpl(RecruiterRepository recruiterRepository) {
        this.recruiterRepository = recruiterRepository;
    }

    @Override
    public List<Recruiter> getRecruiters() {

        return recruiterRepository.retrieveRecruiters();
    }
}