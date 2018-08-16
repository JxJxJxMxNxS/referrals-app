package com.nearsoft.referrals.service.impl;

import com.nearsoft.referrals.model.Job;
import com.nearsoft.referrals.model.User;
import com.nearsoft.referrals.repository.GitHubJobRepository;
import com.nearsoft.referrals.repository.JobRepository;
import com.nearsoft.referrals.repository.UserRepository;
import com.nearsoft.referrals.service.PushNotificationService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.LinkedList;
import java.util.List;

@Service
public class JobsUpdaterService {

    private GitHubJobRepository gitHubJobRepository;
    private JobRepository jobRepository;
    private UserRepository userRepository;
    private PushNotificationService pushNotificationService;

    public JobsUpdaterService(GitHubJobRepository gitHubJobRepository, JobRepository jobRepository, UserRepository userRepository, PushNotificationService pushNotificationService) {
        this.gitHubJobRepository = gitHubJobRepository;
        this.jobRepository = jobRepository;
        this.userRepository = userRepository;
        this.pushNotificationService = pushNotificationService;
    }

    @PostConstruct
    public void updateLocalRepository() {
        List<Job> jobs = gitHubJobRepository.retrieveJobs();

        jobs.forEach((job) -> {
            if (jobRepository.findByTitle(job.getTitle()).size() == 0)
                jobRepository.save(job);
        });
        List<User> users = userRepository.findAll();
        List<String> tokenDevices = new LinkedList<>();
        users.forEach(user -> tokenDevices.add(user.getToken_device()));
        this.pushNotificationService.sendNotification(tokenDevices);
    }

}
