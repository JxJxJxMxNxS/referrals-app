package com.nearsoft.referrals.service.impl;

import com.nearsoft.referrals.model.Recruiter;
import com.nearsoft.referrals.repository.RecruiterRepository;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;


@Service
public class RecruitersUpdaterService {
    private RecruiterRepository recruiterRepository;

    public RecruitersUpdaterService(RecruiterRepository recruiterRepository) {
        this.recruiterRepository = recruiterRepository;
    }

    @PostConstruct
    public void updateRecruiters() {
        Recruiter recruiter1 = new Recruiter();
        Recruiter recruiter2 = new Recruiter();
        Recruiter recruiter3 = new Recruiter();
        Recruiter recruiter4 = new Recruiter();
        Recruiter recruiter5 = new Recruiter();
        Recruiter recruiter6 = new Recruiter();
        Recruiter recruiter7 = new Recruiter();


        recruiter1.setName("Adriana Quijada");
        recruiter1.setEmail("aquijada@nearsoft.com");
        recruiter1.setPicture("https://ca.slack-edge.com/T02CND36A-U4NDMJVKQ-058a4a43056d-512");
        recruiterRepository.save(recruiter1);

        recruiter2.setName("Alejandra Landavazo");
        recruiter2.setEmail("alandavazo@nearsoft.com");
        recruiter2.setPicture("https://ca.slack-edge.com/T02CND36A-U031CSG5Z-84a3780a8bd2-512");
        recruiterRepository.save(recruiter2);

        recruiter3.setName("Dora Cortazar");
        recruiter3.setEmail("dcortazar@nearsoft.com");
        recruiter3.setPicture("https://ca.slack-edge.com/T02CND36A-U02KKJD0X-66aa9ac6c1f9-512");
        recruiterRepository.save(recruiter3);

        recruiter4.setName("Liz Baray");
        recruiter4.setEmail("lbaray@nearsoft.com");
        recruiter4.setPicture("https://ca.slack-edge.com/T02CND36A-U02KM5KQ4-b80facaf9ba6-512");
        recruiterRepository.save(recruiter4);

        recruiter5.setName("Priscila Valenzuela");
        recruiter5.setEmail("pvalenzuela@nearsoft.com");
        recruiter5.setPicture("https://ca.slack-edge.com/T02CND36A-U07051VD1-0e19eb428cd8-512");
        recruiterRepository.save(recruiter5);

        recruiter6.setName("Test recruiter");
        recruiter6.setEmail("pon.dead@gmail.com");
        recruiter6.setPicture("http://i.imgur.com/NXnHirE.jpg");
        recruiterRepository.save(recruiter6);

        recruiter7.setName("Test recruiter2");
        recruiter7.setEmail("htovar@nearsoft.com");
        recruiter7.setPicture("http://i.imgur.com/NXnHirE.jpg");
        recruiterRepository.save(recruiter7);

    }

}
