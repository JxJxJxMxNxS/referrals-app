package com.nearsoft.referrals.repository;

import com.nearsoft.referrals.model.Recruiter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecruiterRepository extends JpaRepository<Recruiter, Long> {
    List<Recruiter> findByName(String title);
}
