package com.nearsoft.referrals.service;

import com.nearsoft.referrals.model.ReferBody;
import freemarker.template.TemplateException;

import javax.mail.MessagingException;
import java.io.IOException;

public interface MailerService {
    void sendEmail(ReferBody referBody, String fileName) throws MessagingException, IOException, TemplateException;
}
