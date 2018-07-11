package com.nearsoft.referrals.service.impl;

import com.nearsoft.referrals.model.User;
import com.nearsoft.referrals.repository.UserRepository;
import com.nearsoft.referrals.service.UsersService;
import org.springframework.stereotype.Service;

@Service
public class UsersServiceImpl implements UsersService {

    private UserRepository userRepository;

    public UsersServiceImpl(UserRepository userRepository) {
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
