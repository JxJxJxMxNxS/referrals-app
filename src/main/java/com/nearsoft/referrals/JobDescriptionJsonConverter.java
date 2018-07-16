package com.nearsoft.referrals;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nearsoft.referrals.exception.ReferralsAppException;
import com.nearsoft.referrals.model.JobDescription;

import javax.persistence.AttributeConverter;
import java.io.IOException;

public class JobDescriptionJsonConverter implements AttributeConverter<JobDescription, String> {

    public JobDescriptionJsonConverter() {
    }

    @Override
    public String convertToDatabaseColumn(JobDescription jobDescription) {
        ObjectMapper objectMapper;
        objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(jobDescription);
        } catch (JsonProcessingException e) {
            throw new ReferralsAppException("Could not convert to Json", e);
        }
    }

    @Override
    public JobDescription convertToEntityAttribute(String json) {
        ObjectMapper objectMapper;
        objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(json, JobDescription.class);
        } catch (IOException e) {
            throw new ReferralsAppException("Could not convert from Json", e);
        }
    }
}
