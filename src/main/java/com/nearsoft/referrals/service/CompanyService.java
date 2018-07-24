package com.nearsoft.referrals.service;

import com.nearsoft.referrals.model.Company;

import java.util.List;

public interface CompanyService {
    List<Company> getCompanies();

    void storeCompanyIfNotExists(Company company);

}
