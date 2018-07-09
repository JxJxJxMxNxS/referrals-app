package com.Nearsoft.referrals.service.impl;

import com.Nearsoft.referrals.model.User;
import com.Nearsoft.referrals.repository.UserRepository;
import com.Nearsoft.referrals.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean registerUser(String email, String name) {
        User newUser = new User();
        newUser.setEmail(email);
        newUser.setName(name);
        userRepository.save(newUser);
        return true;
    }
}
