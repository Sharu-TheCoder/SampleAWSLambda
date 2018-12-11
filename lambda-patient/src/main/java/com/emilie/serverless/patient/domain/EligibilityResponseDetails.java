package com.emilie.serverless.patient.domain;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBVersionAttribute;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;

@DynamoDBTable(tableName = "Utilize DynamoDBMapperConfig")
@JsonIgnoreProperties(ignoreUnknown = true)
public class EligibilityResponseDetails {

    private String id;
    private String questionId;
    private String trialId;
    private String patientId;
    private String answer;
    private Date createdDate;
    private Long versionNumber;

    @DynamoDBHashKey(attributeName = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @DynamoDBAttribute(attributeName = "question_id")
    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(final String questionId) {
        this.questionId = questionId;
    }

    @DynamoDBAttribute(attributeName = "patient_id")
    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(final String patientId) {
        this.patientId = patientId;
    }

    @DynamoDBAttribute(attributeName = "trial_id")
    public String getTrialId() {
        return trialId;
    }

    public void setTrialId(final String trialId) {
        this.trialId = trialId;
    }

    @DynamoDBAttribute(attributeName = "answer")
    public String getAnswer() {
        return answer;
    }

    public void setAnswer(final String answer) {
        this.answer = answer;
    }

    @DynamoDBAttribute(attributeName = "created_date")
    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(final Date createdDate) {
        this.createdDate = createdDate;
    }

    @DynamoDBVersionAttribute
    @DynamoDBAttribute(attributeName = "version_number")
    public Long getVersionNumber() {
        return versionNumber;
    }

    public void setVersionNumber(final Long versionNumber) {
        this.versionNumber = versionNumber;
    }

}