package com.nearsoft.referrals.service.impl;

import com.nearsoft.referrals.model.Recruiter;
import com.nearsoft.referrals.repository.RecruiterRepository;
import com.nearsoft.referrals.service.RecruiterService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecruiterServiceImpl implements RecruiterService {

    private RecruiterRepository recruiterRepository;

    public RecruiterServiceImpl(RecruiterRepository recruiterRepository) {
        this.recruiterRepository = recruiterRepository;
    }

    @Override
    public List<Recruiter> getRecruiters() {
        return recruiterRepository.findAll();
    }
}