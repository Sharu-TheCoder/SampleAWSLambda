package com.emilie.serverless.patient.function;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.emilie.serverless.patient.domain.EligibilityResponseDetails;
import com.emilie.serverless.patient.domain.Fields;
import com.emilie.serverless.patient.domain.request.ApiGatewayRequest;
import com.emilie.serverless.patient.domain.response.ApiGatewayResponse;
import com.emilie.serverless.patient.serialization.DefaultMapper;
import com.emilie.serverless.patient.util.response.ResponseUtil;

import java.io.IOException;
import java.util.Date;

import static com.amazonaws.util.StringUtils.isNullOrEmpty;
import static com.emilie.serverless.patient.domain.Fields.TRIAL_ID;
import static com.emilie.serverless.patient.util.response.ResponseUtil.internalServerError;
import static com.emilie.serverless.patient.util.response.ResponseUtil.jsonOk;

public class AddEligibilityResponseHandler implements RequestHandler<ApiGatewayRequest, ApiGatewayResponse> {

    private static final AmazonDynamoDB CLIENT = AmazonDynamoDBClientBuilder.standard().build();
    private static final DynamoDBMapper MAPPER = new DynamoDBMapper(CLIENT);

    private static final String TABLE_NAME = System.getenv("TABLE_NAME");

    private static final DynamoDBMapperConfig TABLE_CONFIG =
            new DynamoDBMapperConfig
                    .Builder()
                    .withTableNameOverride(DynamoDBMapperConfig.TableNameOverride.withTableNameReplacement(TABLE_NAME))
                    .build();

    @Override
    public ApiGatewayResponse handleRequest(final ApiGatewayRequest request, final Context context) {

        if (!request.getHttpMethod().equals("POST") || isNullOrEmpty(request.getBody()) || !request.getPathParameters().containsKey(TRIAL_ID)) {
            return ResponseUtil.badRequest("POST method body and path parameter is required");
        }

        final String trialId = request.getPathParameters().get(TRIAL_ID);

        try {

            final EligibilityResponseDetails eligibilityResponseDetails = DefaultMapper.objectMapper.readValue(request.getBody(), EligibilityResponseDetails.class);

            //id = trialId, patientId, questionId
            final String id = Fields.generateEligibilityResponseId(
                    trialId,
                    eligibilityResponseDetails.getPatientId(),
                    eligibilityResponseDetails.getQuestionId());

            eligibilityResponseDetails.setId(id);
            eligibilityResponseDetails.setCreatedDate(new Date());
            eligibilityResponseDetails.setTrialId(trialId);

            //save
            MAPPER.save(eligibilityResponseDetails, TABLE_CONFIG);

            return jsonOk(eligibilityResponseDetails);

        } catch (IOException e) {
            return internalServerError(e.getMessage());
        }

    }

}