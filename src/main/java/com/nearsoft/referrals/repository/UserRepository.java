package com.nearsoft.referrals.repository;

import com.nearsoft.referrals.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByemail(String email);

    User findByName(String name);
}
