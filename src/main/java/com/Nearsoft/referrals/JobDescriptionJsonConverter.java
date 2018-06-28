package com.Nearsoft.referrals;

import com.Nearsoft.referrals.model.JobDescription;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.AttributeConverter;
import java.io.IOException;

public class JobDescriptionJsonConverter implements AttributeConverter<JobDescription, String> {

    private ObjectMapper objectMapper;

    public JobDescriptionJsonConverter() {
    }

    public JobDescriptionJsonConverter(ObjectMapper objectMapper) {

        this.objectMapper = objectMapper;
    }

    @Override
    public String convertToDatabaseColumn(JobDescription jobDescription) {
        this.objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(jobDescription);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Could not convert to Json", e);
        }
    }

    @Override
    public JobDescription convertToEntityAttribute(String json) {
        this.objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(json, JobDescription.class);
        } catch (IOException e) {
            throw new RuntimeException("Could not convert from Json", e);
        }
    }
}
