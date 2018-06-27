package com.Nearsoft.referrals.repository;

import com.Nearsoft.referrals.model.Recruiter;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RecruiterRepository extends CrudRepository<Recruiter, Long> {
    List<Recruiter> findByName(String title);
}
