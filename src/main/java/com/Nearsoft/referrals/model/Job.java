package com.Nearsoft.referrals.model;

public class Job{
    private String title;
    private JobDescription description;
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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