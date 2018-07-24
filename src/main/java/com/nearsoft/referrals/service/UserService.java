package com.nearsoft.referrals.service;

import com.nearsoft.referrals.model.User;

public interface UserService {
    Long getPrincipalId();

    User getPrincipalUser(String email);

}
