package com.nearsoft.referrals.repository;

import com.nearsoft.referrals.model.Recruiter;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecruiterRepository extends CrudRepository<Recruiter, Long> {
    List<Recruiter> findByName(String title);
}
