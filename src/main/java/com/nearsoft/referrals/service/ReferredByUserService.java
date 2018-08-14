package com.nearsoft.referrals.service;

import com.nearsoft.referrals.model.ReferBody;
import com.nearsoft.referrals.model.ReferredByUser;

import java.util.List;

public interface ReferredByUserService {
    void storeReferred(ReferBody referBody);

    List<ReferredByUser> findByReferUser(String email);
}
