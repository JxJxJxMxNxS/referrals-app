package com.nearsoft.referrals.repository;

import com.nearsoft.referrals.model.ReferredByUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReferredByUserRepository extends JpaRepository<ReferredByUser, Long> {
    ReferredByUser findByEmail(String email);
}
