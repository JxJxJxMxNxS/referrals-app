package com.nearsoft.referrals.service.impl;

import com.nearsoft.referrals.model.Job;
import com.nearsoft.referrals.model.Recruiter;
import com.nearsoft.referrals.model.ReferBody;
import com.nearsoft.referrals.repository.JobRepository;
import com.nearsoft.referrals.repository.RecruiterRepository;
import com.nearsoft.referrals.service.JobService;
import com.nearsoft.referrals.service.MailerService;
import com.nearsoft.referrals.service.StorageService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Service
public class MailerServiceImpl implements MailerService {

    private JavaMailSender emailSender;
    private StorageService storageService;
    private RecruiterRepository recruiterRepository;
    private JobService jobService;
    private JobRepository jobRepository;
    private Configuration freemarkerConfig;
    @Value("${logo_img_url}")
    private String logo_img_url;

    public MailerServiceImpl(JavaMailSender emailSender, StorageService storageService, RecruiterRepository recruiterRepository,
                             JobService jobService, JobRepository jobRepository, Configuration freemarkerConfig) {
        this.emailSender = emailSender;
        this.storageService = storageService;
        this.recruiterRepository = recruiterRepository;
        this.jobService = jobService;
        this.jobRepository = jobRepository;
        this.freemarkerConfig = freemarkerConfig;
    }

    @Async
    @Override
    public void sendEmail(ReferBody referBody, String fileName) throws MessagingException, IOException, TemplateException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name());
        String bodyMessage;
        helper.addInline("logo.png", new ClassPathResource(logo_img_url));
        Recruiter recruiter;
        Job job;

        Template t = freemarkerConfig.getTemplate("ReferredTemplate.ftl");
        recruiter = recruiterRepository.findById(referBody.getRecruiter_id()).get();
        job = jobRepository.findById(referBody.getJob_id()).get();
        Map model = new HashMap();
        model.put("referred_name", referBody.getReferred_name());
        model.put("referred_email", referBody.getReferred_email());
        model.put("recruiter_name", recruiter.getName());
        model.put("position_title", job.getTitle());
        bodyMessage = FreeMarkerTemplateUtils.processTemplateIntoString(t, model);

        helper.setTo(recruiter.getEmail());
        if (referBody.getStrong_referral() != null)
            helper.setSubject(referBody.getStrong_referral() ? "Strong referal to an opening position" : "Referred to an opening position");
        else
            helper.setSubject("Referred to an opening position");
        helper.setText(bodyMessage, true);

        if (fileName != null) {
            FileSystemResource file = storageService.getFileSystemResource(fileName);
            helper.addAttachment(fileName, file);
        }
        emailSender.send(message);
        storageService.deleteFile(fileName);
    }

}
