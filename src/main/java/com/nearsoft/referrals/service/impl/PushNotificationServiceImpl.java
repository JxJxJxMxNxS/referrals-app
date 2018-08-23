package com.nearsoft.referrals.service.impl;

import com.nearsoft.referrals.service.PushNotificationService;
import javapns.Push;
import javapns.communication.exceptions.CommunicationException;
import javapns.communication.exceptions.KeystoreException;
import javapns.notification.PushNotificationPayload;
import javapns.notification.PushedNotification;
import javapns.notification.ResponsePacket;
import org.apache.log4j.BasicConfigurator;
import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PushNotificationServiceImpl implements PushNotificationService {

    private final static Logger LOGGER = LoggerFactory.getLogger(PushNotificationServiceImpl.class);
    @Value("${ios.apnPassword}")
    private String apnPassword;
    @Value("${ios.certificateName}")
    private String iosCertificateName;

    @Override
    public void sendNotification(List<String> tokenDevices) {
        BasicConfigurator.configure();

        try {
            PushNotificationPayload payload = PushNotificationPayload.complex();
            payload.addAlert("New openings available!");
            payload.addBadge(1);
            payload.addSound("default");
            payload.addCustomDictionary("id", "1");
            List<PushedNotification> NOTIFICATIONS = Push.payload(payload, iosCertificateName, apnPassword, false, tokenDevices);
            for (PushedNotification NOTIFICATION : NOTIFICATIONS) {
                if (NOTIFICATION.isSuccessful()) {
                    LOGGER.debug("Push notification sent to: " + NOTIFICATION.getDevice().getToken());
                } else {
                    String invalidToken = NOTIFICATION.getDevice().getToken();
                    Exception exception = NOTIFICATION.getException();
                    exception.printStackTrace();
                    ResponsePacket errorResponse = NOTIFICATION.getResponse();
                    if (errorResponse != null) {
                        LOGGER.error(errorResponse.getMessage());
                    }
                }
            }
        } catch (CommunicationException e) {
            LOGGER.error(e.getMessage());
        } catch (KeystoreException e) {
            LOGGER.error(e.getMessage());
        } catch (JSONException e) {
            LOGGER.error(e.getMessage());
        }
    }
}
