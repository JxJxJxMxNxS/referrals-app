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
        List<Recruiter> recruitersList = new ArrayList<>();
       /* Recruiter recruiter1 = new Recruiter();
        Recruiter recruiter2 = new Recruiter();

        recruiter1.setName("Recruiter1");
        recruiter1.setEmail("Recruiter1@nearsoft.com");
        recruiter1.setPicture("");
        recruiter2.setName("Recruiter2");
        recruiter2.setEmail("Recruiter2@nearsoft.com");
        recruiter2.setPicture("");


        recruitersList.add(recruiter1);
        recruitersList.add(recruiter2);*/
        recruiterRepository.findAll().forEach(recruitersList::add);
        return recruitersList;
    }
}