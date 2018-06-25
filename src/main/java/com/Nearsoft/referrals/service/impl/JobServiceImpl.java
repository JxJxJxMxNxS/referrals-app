package com.Nearsoft.referrals.service.impl;

import com.Nearsoft.referrals.model.Job;
import com.Nearsoft.referrals.model.JobDescription;
import com.Nearsoft.referrals.service.JobService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class JobServiceImpl implements JobService{
    
    @Override
    public List<Job> getJobs() {
        List<Job> jobs= new ArrayList<Job>();
        jobs = parseJobs();
        return jobs;
    }

    public String fetchReadme(){
        String rawTextReadme;
        String uri = "https://raw.githubusercontent.com/Nearsoft/jobs/master/readme.md";
        RestTemplate restTemplate = new RestTemplate();
        rawTextReadme = restTemplate.getForObject(uri, String.class);
        return rawTextReadme;
    }

    public List<Job> parseJobs(){
        List<Job> parsedJobsList = new ArrayList<Job>();
        String rawText = fetchReadme();
        Job parsedJob;      
        Pattern pattern = Pattern.compile("\\* \\[(.*?)\\]\\(#");
        Matcher matcher = pattern.matcher(rawText);

        while(matcher.find()) {
            parsedJob = new Job();
            parsedJob.setTitle(matcher.group(1));
            parsedJobsList.add(parsedJob);
        }
        return parsedJobsList;
    }
}