package com.nearsoft.referrals.repository;

import com.nearsoft.referrals.model.Company;
import com.nearsoft.referrals.model.MostReferredCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
    Optional<Company> findByName(String name);

    @Query(nativeQuery = true, value = "SELECT company.name, count(referred_by_user.company_id) FROM company INNER JOIN referred_by_user ON company.id = referred_by_user.company_id group by company.name order by count desc")
    List<MostReferredCompany> getMostCommonCompanies();
}