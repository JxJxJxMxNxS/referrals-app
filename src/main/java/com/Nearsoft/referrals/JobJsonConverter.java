package com.Nearsoft.referrals;

import com.Nearsoft.referrals.model.Job;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.AttributeConverter;
import java.io.IOException;

public class JobJsonConverter implements AttributeConverter<Job, String> {

    private ObjectMapper objectMapper;

    public JobJsonConverter() {
    }

    public JobJsonConverter(ObjectMapper objectMapper) {

        this.objectMapper = objectMapper;
    }

    @Override
    public String convertToDatabaseColumn(Job job) {
        try {
            return objectMapper.writeValueAsString(job);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Could not convert to Json", e);
        }
    }

    @Override
    public Job convertToEntityAttribute(String json) {
        try {
            return objectMapper.readValue(json, Job.class);
        } catch (IOException e) {
            throw new RuntimeException("Could not convert from Json", e);
        }
    }
}
