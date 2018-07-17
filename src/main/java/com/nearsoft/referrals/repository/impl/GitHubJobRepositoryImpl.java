package com.nearsoft.referrals.repository.impl;

import com.nearsoft.referrals.model.Job;
import com.nearsoft.referrals.model.JobDescription;
import com.nearsoft.referrals.repository.GitHubJobRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Repository
public class GitHubJobRepositoryImpl implements GitHubJobRepository {
    private static final String EXPRESSION_TO_FIND_JOB_TITLES = "\\* \\[(.*?)\\]\\(#";
    private static final String REQUIREMENTS = "Requirements";
    private static final String RESPONSIBILITIES = "Responsibilities";
    private static final String SKILLS = "Skills";
    @Value("${referrals.github.url}")
    private String gitUrl;

    public List<Job> retrieveJobs() {
        List<Job> parsedJobsList = new ArrayList<>();
        String rawText = fetchReadme();
        Pattern pattern = Pattern.compile(EXPRESSION_TO_FIND_JOB_TITLES);
        Matcher matcher = pattern.matcher(rawText);
        String expressionToSeek;

        while (matcher.find()) {
            Job parsedJob = new Job();

            parsedJob.setTitle(matcher.group(1));
            rawText = rawText.replaceAll("###", "---");
            expressionToSeek = "## " + parsedJob.getTitle();
            int startIndex = rawText.indexOf(expressionToSeek);
            int finalIndex = rawText.indexOf("## ", startIndex + expressionToSeek.length());
            String jobDescription = rawText.substring(startIndex, finalIndex);
            JobDescription parsedJobDescription = parseDescription(jobDescription);
            parsedJob.setJobDescription(parsedJobDescription);
            parsedJobsList.add(parsedJob);

        }
        return parsedJobsList;

    }

    private String fetchReadme() {
        RestTemplate restTemplate = new RestTemplate();

        return restTemplate.getForObject(gitUrl, String.class);
    }

    private JobDescription parseDescription(String textDescription) {
        JobDescription jobDescription = new JobDescription();
        List<String> descriptionList;

        if (textDescription.contains("---")) {
            Pattern pattern = Pattern.compile("--- (.+)\\s");
            Matcher matcher = pattern.matcher(textDescription);

            findDescriptionTitles(textDescription, jobDescription, matcher);
        } else {
            descriptionList = parseWithAsterisk(textDescription);
            jobDescription.setGenerals(descriptionList);
        }
        return jobDescription;
    }

    private void findDescriptionTitles(String textDescription, JobDescription jobDescription, Matcher matcher) {
        String expressionToSeek;
        int startIndex;
        int finalIndex;
        List<String> descriptionList;
        while (matcher.find()) {
            expressionToSeek = "--- " + matcher.group(1);
            startIndex = textDescription.indexOf(expressionToSeek);
            finalIndex = textDescription.indexOf("--- ", startIndex + expressionToSeek.length());
            finalIndex = (finalIndex > -1) ? finalIndex : (textDescription.length() - 1);
            String description = textDescription.substring(startIndex, finalIndex);

            descriptionList = parseWithAsterisk(description);

            classifyDescription(jobDescription, descriptionList, matcher);

        }
    }

    private void classifyDescription(JobDescription jobDescription, List<String> descriptionList, Matcher matcher) {
        if (matcher.group(1).contains(REQUIREMENTS)) {
            jobDescription.setRequirements(descriptionList);
        } else if (matcher.group(1).contains(RESPONSIBILITIES)) {
            jobDescription.setResponsibilities(descriptionList);
        } else if (matcher.group(1).contains(SKILLS)) {
            jobDescription.setSkills(descriptionList);
        } else {
            jobDescription.setGenerals(descriptionList);
        }
    }

    private List<String> parseWithAsterisk(String asterisks) {
        List<String> descriptionList = new ArrayList<>();
        Pattern pattern = Pattern.compile("\\* (.+)\\s");
        Matcher matcher = pattern.matcher(asterisks);

        while (matcher.find()) {
            descriptionList.add(matcher.group(1));
        }

        return descriptionList;
    }
}
