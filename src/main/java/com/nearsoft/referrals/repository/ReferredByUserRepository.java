package com.nearsoft.referrals.repository;

import com.nearsoft.referrals.model.ReferredByUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReferredByUserRepository extends JpaRepository<ReferredByUser, Long> {
    ReferredByUser findByEmail(String email);

    @Query(value = "SELECT referred FROM ReferredByUser referred WHERE referred.email = :email")
    List<ReferredByUser> findByReferUser(@Param("email") String email);

}
