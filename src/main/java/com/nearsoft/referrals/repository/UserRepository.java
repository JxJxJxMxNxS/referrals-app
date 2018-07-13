package com.nearsoft.referrals.repository;

import com.nearsoft.referrals.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByemail(String email);
}
