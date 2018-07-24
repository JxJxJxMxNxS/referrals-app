package com.nearsoft.referrals.service.impl;

import com.nearsoft.referrals.model.Company;
import com.nearsoft.referrals.repository.CompanyRepository;
import com.nearsoft.referrals.service.CompanyService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {

    private CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public List<Company> getCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public void storeCompanyIfNotExists(Company company) {
        companyRepository.findByName(company.getName()).orElseGet(() -> companyRepository.save(company));
    }


}
