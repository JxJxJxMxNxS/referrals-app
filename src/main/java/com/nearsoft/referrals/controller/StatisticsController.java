package com.nearsoft.referrals.controller;

import com.nearsoft.referrals.model.MostReferredCompany;
import com.nearsoft.referrals.model.MostReferredJob;
import com.nearsoft.referrals.service.CompanyService;
import com.nearsoft.referrals.service.JobService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StatisticsController {

    private static final Logger LOGGER = LoggerFactory.getLogger(StatisticsController.class);
    private JobService jobService;
    private CompanyService companyService;

    public StatisticsController(JobService jobService, CompanyService companyService) {
        this.jobService = jobService;
        this.companyService = companyService;
    }

    @RequestMapping(value = "mostreferredjob")
    public List<MostReferredJob> mostReferred() {
        LOGGER.trace("Retrieving the most referred jobs");
        List<MostReferredJob> jobs = jobService.getMostReferredJob();
        LOGGER.trace("Most referred jobs: {}", jobs);
        return jobs;
    }

    @RequestMapping(value = "mostreferredcompanies")
    public List<MostReferredCompany> mostReferredCompany() {
        LOGGER.trace("Retrieving the most referred companies");
        List<MostReferredCompany> companies = companyService.getMostReferredCompanies();
        LOGGER.trace("Most referred companies: {}", companies);
        return companies;
    }


}
