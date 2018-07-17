package com.nearsoft.referrals.model;

import org.springframework.web.multipart.MultipartFile;

public class ReferBody {
    private MultipartFile resume_file;
    private Long recruiter_id;
    private Long job_id;
    private String referred_name;
    private String referred_email;
    private Boolean strong_referral;
    private Integer strong_referral_year;
    private Integer strong_referral_month;
    private String strong_referral_where;
    private String strong_referral_why;

    public MultipartFile getResume_file() {
        return resume_file;
    }

    public void setResume_file(MultipartFile resume_file) {
        this.resume_file = resume_file;
    }

    public Long getRecruiter_id() {
        return recruiter_id;
    }

    public void setRecruiter_id(Long recruiter_id) {
        this.recruiter_id = recruiter_id;
    }

    public Long getJob_id() {
        return job_id;
    }

    public void setJob_id(Long job_id) {
        this.job_id = job_id;
    }

    public String getReferred_name() {
        return referred_name;
    }

    public void setReferred_name(String referred_name) {
        this.referred_name = referred_name;
    }

    public String getReferred_email() {
        return referred_email;
    }

    public void setReferred_email(String referred_email) {
        this.referred_email = referred_email;
    }

    public Boolean getStrong_referral() {
        return strong_referral;
    }

    public void setStrong_referral(Boolean strong_referral) {
        this.strong_referral = strong_referral;
    }

    public Integer getStrong_referral_year() {
        return strong_referral_year;
    }

    public void setStrong_referral_year(Integer strong_referral_year) {
        this.strong_referral_year = strong_referral_year;
    }

    public Integer getStrong_referral_month() {
        return strong_referral_month;
    }

    public void setStrong_referral_month(Integer strong_referral_month) {
        this.strong_referral_month = strong_referral_month;
    }

    public String getStrong_referral_where() {
        return strong_referral_where;
    }

    public void setStrong_referral_where(String strong_referral_where) {
        this.strong_referral_where = strong_referral_where;
    }

    public String getStrong_referral_why() {
        return strong_referral_why;
    }

    public void setStrong_referral_why(String strong_referral_why) {
        this.strong_referral_why = strong_referral_why;
    }
}
