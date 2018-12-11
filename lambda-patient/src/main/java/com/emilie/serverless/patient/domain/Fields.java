package com.emilie.serverless.patient.domain;

public interface Fields {

    String TRIAL_ID = "trialId";

    static String generateEligibilityResponseId(final String trialId, final String patientId, final String questionId) {
        return trialId + "::" + patientId + "::" + questionId;
    }

}