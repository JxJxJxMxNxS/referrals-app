package com.Nearsoft.referrals.service.impl;

import com.Nearsoft.referrals.model.Recruiter;
import com.Nearsoft.referrals.service.RecruiterService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecruiterServiceImpl implements RecruiterService {

    @Override
    public List<Recruiter> getRecruiters() {
        List<Recruiter> recruitersList = new ArrayList<>();
        Recruiter recruiter1 = new Recruiter();
        Recruiter recruiter2 = new Recruiter();
        Recruiter recruiter3 = new Recruiter();
        Recruiter recruiter4 = new Recruiter();
        Recruiter recruiter5 = new Recruiter();

        recruiter1.setName("Adriana Quijada");
        recruiter1.setEmail("aquijada@nearsoft.com");
        recruiter1.setPicture("");
        recruiter2.setName("Alejandra Landavazo");
        recruiter2.setEmail("alandavazo@nearsoft.com");
        recruiter2.setPicture("");
        recruiter3.setName("Dora Cortazar");
        recruiter3.setEmail("dcortazar@nearsoft.com");
        recruiter3.setPicture("");
        recruiter4.setName("Liz Baray");
        recruiter4.setEmail("lbaray@nearsoft.com");
        recruiter4.setPicture("");
        recruiter5.setName("Priscila Valenzuela");
        recruiter5.setEmail("pvalenzuela@nearsoft.com");
        recruiter5.setPicture("");




        recruitersList.add(recruiter1);
        recruitersList.add(recruiter2);
        recruitersList.add(recruiter3);
        recruitersList.add(recruiter4);
        recruitersList.add(recruiter5);

        return recruitersList;
    }
}