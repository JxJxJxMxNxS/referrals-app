package com.nearsoft.referrals.service.impl;

import com.nearsoft.referrals.model.Company;
import com.nearsoft.referrals.model.ReferBody;
import com.nearsoft.referrals.model.ReferredByUser;
import com.nearsoft.referrals.repository.CompanyRepository;
import com.nearsoft.referrals.repository.ReferredByUserRepository;
import com.nearsoft.referrals.service.ReferredByUserService;
import org.springframework.stereotype.Service;

@Service
public class ReferredByUserServiceImpl implements ReferredByUserService {

    private ReferredByUserRepository referredByUserRepository;
    private CompanyRepository companyRepository;

    public ReferredByUserServiceImpl(ReferredByUserRepository referredByUserRepository, CompanyRepository companyRepository) {
        this.referredByUserRepository = referredByUserRepository;
        this.companyRepository = companyRepository;
    }

    @Override
    public void storeReferred(ReferBody referBody) {
        Company company;
        ReferredByUser referredByUser = new ReferredByUser();

        if (referBody.getStrong_referral_where() != null) {
            company = companyRepository.findByName(referBody.getStrong_referral_where()).get();
            referredByUser.setCompanyId(company.getId());
        }

        referredByUser.setName(referBody.getReferred_name());
        referredByUser.setEmail(referBody.getReferred_email());
        referredByUser.setJobId(referBody.getJob_id());
        referredByUser.setRecruiterId(referBody.getRecruiter_id());
        referredByUser.setUserId(referBody.getUser_id());

        referredByUserRepository.save(referredByUser);
    }
}
