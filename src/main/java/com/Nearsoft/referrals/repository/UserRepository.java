package com.Nearsoft.referrals.repository;

import com.Nearsoft.referrals.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {
    List<User> findByemail(String email);
}
