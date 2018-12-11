package com.emilie.serverless.patient.function;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedScanList;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.emilie.serverless.patient.domain.EligibilityResponseDetails;
import com.emilie.serverless.patient.domain.request.ApiGatewayRequest;
import com.emilie.serverless.patient.domain.response.ApiGatewayResponse;
import com.google.common.collect.ImmutableMap;

import java.util.Map;

import static com.emilie.serverless.patient.domain.Fields.TRIAL_ID;
import static com.emilie.serverless.patient.util.response.ResponseUtil.badRequest;
import static com.emilie.serverless.patient.util.response.ResponseUtil.jsonOk;

public class GetEligibilityResponseHandler implements RequestHandler<ApiGatewayRequest, ApiGatewayResponse> {

    private static final String PATIENT_ID = "1";

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

        if (!request.getHttpMethod().equals("GET") || !request.getPathParameters().containsKey(TRIAL_ID)) {
            return badRequest("GET method and trial id are required");
        }

        final String trialId = request.getPathParameters().get(TRIAL_ID);
        final Map<String, AttributeValue> attributes =
                ImmutableMap.of(
                        ":patientId", new AttributeValue().withS(PATIENT_ID),
                        ":trialId", new AttributeValue().withS(trialId));
        final DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
                .withFilterExpression("trial_id = :trialId AND patient_id = :patientId")
                .withExpressionAttributeValues(attributes);

        final PaginatedScanList<EligibilityResponseDetails> responses = MAPPER.scan(EligibilityResponseDetails.class, scanExpression, TABLE_CONFIG);

        return jsonOk(responses);

    }

}