package com.nearsoft.referrals.controller;

import com.nearsoft.referrals.model.Company;
import com.nearsoft.referrals.service.CompanyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CompaniesController {
    private CompanyService companyService;
    private static final Logger LOGGER = LoggerFactory.getLogger(CompaniesController.class);

    public CompaniesController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @RequestMapping(value = "/companies")
    public List<Company> getJobs() {
        List<Company> companies;
        LOGGER.trace("Retrieving companies");
        companies = companyService.getCompanies();
        LOGGER.trace("Retrieved companies: {}", companies);
        return companies;
    }
}