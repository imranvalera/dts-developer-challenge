package uk.gov.hmcts.reform.dev.gateway;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface APICallService {

    ApiResponseWrapper postRequest(String url, Object requestBody) throws JsonProcessingException;
    ApiResponseWrapper getRequest(String url);
    ApiResponseWrapper deleteRequest(String url, Object requestBody);
    ApiResponseWrapper getRequestById(String url, Object requestBody);
}
