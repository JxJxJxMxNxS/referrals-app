package com.nearsoft.referrals.service;

import java.util.List;

public interface PushNotificationService {
    void sendNotification(List<String> tokenDevices);
}
