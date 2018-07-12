package com.nearsoft.referrals.model;

import org.springframework.web.multipart.MultipartFile;

public class ReferBody {
    MultipartFile resume_file;
    Long recruiter_id;
    Long job_id;
    String referred_name;
    String referred_email;
    Boolean strong_referral;
    Integer strong_referral_quantity_time;
    String strong_referral_ago;
    String strong_referral_where;
    String strong_referral_why;

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

    public Integer getStrong_referral_quantity_time() {
        return strong_referral_quantity_time;
    }

    public void setStrong_referral_quantity_time(Integer strong_referral_quantity_time) {
        this.strong_referral_quantity_time = strong_referral_quantity_time;
    }

    public String getStrong_referral_ago() {
        return strong_referral_ago;
    }

    public void setStrong_referral_ago(String strong_referral_ago) {
        this.strong_referral_ago = strong_referral_ago;
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
