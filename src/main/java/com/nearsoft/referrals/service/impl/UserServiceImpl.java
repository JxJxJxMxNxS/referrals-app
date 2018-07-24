package com.nearsoft.referrals.service.impl;

import com.nearsoft.referrals.model.User;
import com.nearsoft.referrals.repository.UserRepository;
import com.nearsoft.referrals.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Long getPrincipalId() {
        return null;
    }

    @Override
    public User getPrincipalUser(String email) {
        return userRepository.findByemail(email);
    }
}
