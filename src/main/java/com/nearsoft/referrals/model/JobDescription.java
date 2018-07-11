package com.nearsoft.referrals.model;

import java.util.List;

public class JobDescription {
    private List<String> requirements;
    private List<String> responsibilities;
    private List<String> skills;
    private List<String> generals;

    public List<String> getRequirements() {
        return requirements;
    }

    public void setRequirements(List<String> requirements) {
        this.requirements = requirements;
    }

    public List<String> getResponsibilities() {
        return responsibilities;
    }

    public void setResponsibilities(List<String> responsibilities) {
        this.responsibilities = responsibilities;
    }

    public List<String> getGenerals() {
        return generals;
    }

    public void setGenerals(List<String> generals) {
        if (this.generals == null)
            this.generals = generals;
        else
            this.generals.addAll(generals);
    }

    public List<String> getSkills() {
        return skills;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }

    @Override
    public String toString() {
        return "JobDescription{" +
                "requirements=" + requirements +
                ", responsibilities=" + responsibilities +
                ", skills=" + skills +
                ", generals=" + generals +
                '}';
    }
}