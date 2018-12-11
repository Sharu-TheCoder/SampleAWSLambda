package com.emilie.serverless.patient.serialization;

import com.fasterxml.jackson.databind.ObjectMapper;

public class DefaultMapper {

    public static final ObjectMapper objectMapper;

    static {
        objectMapper = new ObjectMapper();
    }

}