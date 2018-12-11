package com.emilie.serverless.patient.util.response;

import com.emilie.serverless.patient.domain.response.ApiGatewayResponse;
import com.google.common.collect.ImmutableMap;
import org.apache.http.HttpStatus;

import java.util.Map;

public interface ResponseUtil {

    Map<String, String> JSON_HEADERS = ImmutableMap.of(
            "Content-Type", "application/json",
            "Access-Control-Allow-Credentials", "true",
            "Access-Control-Allow-Headers", "*",
            "Access-Control-Allow-Origin", "*",
            "Access-Control-Allow-Methods", "OPTIONS,GET,PUT,POST,DELETE");

    static ApiGatewayResponse jsonOk(final Object body) {
        return ApiGatewayResponse
                .builder()
                .setStatusCode(HttpStatus.SC_OK)
                .setObjectBody(body)
                .setHeaders(JSON_HEADERS)
                .build();
    }

    static ApiGatewayResponse badRequest(final Object body) {
        return ApiGatewayResponse
                .builder()
                .setStatusCode(HttpStatus.SC_BAD_REQUEST)
                .setObjectBody(body)
                .build();
    }

    static ApiGatewayResponse notFound() {
        return ApiGatewayResponse
                .builder()
                .setStatusCode(HttpStatus.SC_NOT_FOUND)
                .build();
    }

    static ApiGatewayResponse internalServerError(final Object body) {
        return ApiGatewayResponse
                .builder()
                .setStatusCode(HttpStatus.SC_INTERNAL_SERVER_ERROR)
                .setObjectBody(body)
                .build();
    }

}
