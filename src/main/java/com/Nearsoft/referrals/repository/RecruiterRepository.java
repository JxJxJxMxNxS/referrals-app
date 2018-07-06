package com.Nearsoft.referrals.repository;

import com.Nearsoft.referrals.model.Recruiter;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class RecruiterRepository {
    public List<Recruiter> retrieveRecruiters(){
        List<Recruiter> recruitersList = new ArrayList<>();
        Recruiter recruiter1 = new Recruiter();
        Recruiter recruiter2 = new Recruiter();
        Recruiter recruiter3 = new Recruiter();
        Recruiter recruiter4 = new Recruiter();
        Recruiter recruiter5 = new Recruiter();
        Recruiter recruiter6 = new Recruiter();


        recruiter1.setName("Adriana Quijada");
        recruiter1.setEmail("aquijada@nearsoft.com");
        recruiter1.setPicture("https://ca.slack-edge.com/T02CND36A-U4NDMJVKQ-058a4a43056d-512");
        recruiter1.setId(new Long(1));

        recruiter2.setName("Alejandra Landavazo");
        recruiter2.setEmail("alandavazo@nearsoft.com");
        recruiter2.setPicture("https://ca.slack-edge.com/T02CND36A-U031CSG5Z-84a3780a8bd2-512");
        recruiter2.setId(new Long(2));

        recruiter3.setName("Dora Cortazar");
        recruiter3.setEmail("dcortazar@nearsoft.com");
        recruiter3.setPicture("https://ca.slack-edge.com/T02CND36A-U02KKJD0X-66aa9ac6c1f9-512");
        recruiter3.setId(new Long(3));

        recruiter4.setName("Liz Baray");
        recruiter4.setEmail("lbaray@nearsoft.com");
        recruiter4.setPicture("https://ca.slack-edge.com/T02CND36A-U02KM5KQ4-b80facaf9ba6-512");
        recruiter4.setId(new Long(4));

        recruiter5.setName("Priscila Valenzuela");
        recruiter5.setEmail("pvalenzuela@nearsoft.com");
        recruiter5.setPicture("https://ca.slack-edge.com/T02CND36A-U07051VD1-0e19eb428cd8-512");
        recruiter5.setId(new Long(5));

        recruiter6.setName("Test recruiter");
        recruiter6.setEmail("pon.dead@gmail.com");
        recruiter6.setPicture("http://i.imgur.com/NXnHirE.jpg");
        recruiter6.setId(new Long(6));



        recruitersList.add(recruiter1);
        recruitersList.add(recruiter2);
        recruitersList.add(recruiter3);
        recruitersList.add(recruiter4);
        recruitersList.add(recruiter5);
        recruitersList.add(recruiter6);
        return recruitersList;
    }
}
