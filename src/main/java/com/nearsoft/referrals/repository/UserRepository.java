package com.nearsoft.referrals.repository;

import com.nearsoft.referrals.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByemail(String email);
}
