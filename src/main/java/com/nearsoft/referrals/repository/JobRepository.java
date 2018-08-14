package com.nearsoft.referrals.repository;

import com.nearsoft.referrals.model.Job;
import com.nearsoft.referrals.model.MostReferredJob;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {
    List<Job> findByTitle(String title);

    @Query(nativeQuery = true, value = "SELECT job.title, count(referred_by_user.job_id) FROM job INNER JOIN referred_by_user ON job.id = referred_by_user.job_id group by job.title order by count desc")
    List<MostReferredJob> getMostReferredJobs();
}
