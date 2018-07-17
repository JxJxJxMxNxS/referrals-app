package com.nearsoft.referrals.controller;

import com.nearsoft.referrals.model.Company;
import com.nearsoft.referrals.service.CompanyService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CompaniesController {
    private CompanyService companyService;

    public CompaniesController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @RequestMapping(value = "/companies")
    public List<Company> getJobs() {
        return companyService.getCompanies();
    }
}