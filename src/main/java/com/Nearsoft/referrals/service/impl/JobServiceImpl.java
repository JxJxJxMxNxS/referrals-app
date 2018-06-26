package com.Nearsoft.referrals.service.impl;

import com.Nearsoft.referrals.model.Job;
import com.Nearsoft.referrals.model.JobDescription;
import com.Nearsoft.referrals.service.JobService;

import org.springframework.core.type.StandardAnnotationMetadata;
import org.springframework.stereotype.Service;
import org.springframework.util.SystemPropertyUtils;
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
        String jobDescriptionRegExp;
        JobDescription parsedJobDescription;     
        Pattern pattern = Pattern.compile("\\* \\[(.*?)\\]\\(#");
        Matcher matcher = pattern.matcher(rawText);
        String exptoSeek;
        while(matcher.find()) {
            parsedJob = new Job();
            parsedJob.setTitle(matcher.group(1));   
            rawText = rawText.replaceAll("###", "---");
            exptoSeek = "## "+parsedJob.getTitle();
            int startIndex = rawText.indexOf(exptoSeek);
            int finalIndex = rawText.indexOf("## ",startIndex+exptoSeek.length());
            String jobDescription = rawText.substring(startIndex,finalIndex);
            parsedJobDescription = parseDescription(jobDescription);
            parsedJob.setDescription(parsedJobDescription);
            parsedJobsList.add(parsedJob);
            
            
        }
        return parsedJobsList;
    }

    public JobDescription parseDescription(String textDescription){
        JobDescription jobDescription = new JobDescription();
        List<String> descriptionList;
        int startIndex = -1;
        int finalIndex = -1;
        String exptoSeek;
        if(textDescription.contains("---")){
            Pattern pattern = Pattern.compile("--- (.+)\\s");
            Matcher matcher = pattern.matcher(textDescription);
            while(matcher.find()){
                exptoSeek = "--- "+matcher.group(1);
                startIndex = textDescription.indexOf(exptoSeek);
                finalIndex = textDescription.indexOf("--- ",startIndex+exptoSeek.length());
                finalIndex = (finalIndex>-1)?finalIndex:textDescription.length()-1;
                String description = textDescription.substring(startIndex,finalIndex);
                descriptionList = parseWithAsterisk(description);
                if(matcher.group(1).contains("Requirements")){
                    jobDescription.setRequirements(descriptionList);
                }else if(matcher.group(1).contains("Responsibilities")){
                    jobDescription.setResponsibilities(descriptionList);
                }else if(matcher.group(1).contains("Skills")){
                    jobDescription.setSkills(descriptionList);
                }else{
                    jobDescription.setGenerals(descriptionList);
                }
            }   
        }
        else{
            descriptionList = parseWithAsterisk(textDescription);
            jobDescription.setGenerals(descriptionList);
        }
        return jobDescription;
    }

    public List<String> parseWithAsterisk(String asterisks){
        List<String> descriptionList = new ArrayList<String>();
        Pattern pattern = Pattern.compile("\\* (.+)\\s");
            Matcher matcher = pattern.matcher(asterisks);
            while(matcher.find()){
                descriptionList.add(matcher.group(1));
            }
        return descriptionList;
    }
}