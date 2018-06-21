package com.Nearsoft.referrals.model;

public class Job{
    private String title;
    private JobDescription description;

    public String getTitle(){
        return title;
    }

    public JobDescription getDescription(){
        return description;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(JobDescription description){
        this.description = description;
    }
}