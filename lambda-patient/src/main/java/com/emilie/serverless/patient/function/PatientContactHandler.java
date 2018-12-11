package com.emilie.serverless.patient.function;

import static com.amazonaws.util.StringUtils.isNullOrEmpty;
import static com.emilie.serverless.patient.util.response.ResponseUtil.badRequest;
import static com.emilie.serverless.patient.util.response.ResponseUtil.internalServerError;
import static com.emilie.serverless.patient.util.response.ResponseUtil.jsonOk;

import java.io.IOException;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.emilie.serverless.patient.domain.PatientContact;
import com.emilie.serverless.patient.domain.request.ApiGatewayRequest;
import com.emilie.serverless.patient.domain.response.ApiGatewayResponse;
import com.emilie.serverless.patient.serialization.DefaultMapper;

public class PatientContactHandler implements RequestHandler<ApiGatewayRequest, ApiGatewayResponse>{
	
	  private static final AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().build();
	    private static final DynamoDBMapper mapper = new DynamoDBMapper(client);

	    private static final String TABLE_NAME = System.getenv("TABLE_NAME");
	    private static final DynamoDBMapperConfig TABLE_CONFIG =
	            new DynamoDBMapperConfig
	                    .Builder()
	                    .withTableNameOverride(DynamoDBMapperConfig.TableNameOverride.withTableNameReplacement(TABLE_NAME))
	                    .build();

	@Override
	public ApiGatewayResponse handleRequest(ApiGatewayRequest request, Context context) {
		// TODO Auto-generated method stub
		 if (!request.getHttpMethod().equals("POST") || isNullOrEmpty(request.getBody())) {
	            return badRequest("POST method body is required");
	        }

	        try {

	            final PatientContact patientContact = DefaultMapper.objectMapper.readValue(request.getBody(), PatientContact.class);
	           

	         

	            //save
	            mapper.save(patientContact, TABLE_CONFIG);

	            return jsonOk(patientContact);

	        } catch (IOException e) {
	            return internalServerError(e.getMessage());
	        }
	}

}
