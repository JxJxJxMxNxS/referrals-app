package com.Nearsoft.referrals.model;
import java.util.List;
public class JobDescription{
    private List<String> requirements;
    private List<String> responsibilities;
    private List<String> generals;

    public List<String> getRequirements(){
        return requirements;
    }

    public List<String> getResponsibilities(){
        return responsibilities;
    }

    public List<String> getGenerals(){
        return generals;
    }


    public void setRequirements(List<String> requirements){
        this.requirements = requirements;
    }

    public void setResponsibilities(List<String> responsibilities){
        this.responsibilities = responsibilities;
    }

    public void setGenerals(List<String> responsibilities){
        this.responsibilities = responsibilities;
    }


}