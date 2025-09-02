package uk.gov.hmcts.reform.dev.gateway;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import uk.gov.hmcts.reform.dev.exception.ApiGlobalException;
import uk.gov.hmcts.reform.dev.models.reponse.ErrorResponse;

import java.net.URI;
import java.util.Objects;
import java.util.Optional;

import static uk.gov.hmcts.reform.dev.util.Constants.*;

@Service
@Slf4j
public class APICallServiceImpl implements APICallService {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public APICallServiceImpl(ObjectMapper objectMapper, RestTemplate restTemplate) {
        this.objectMapper = objectMapper;
        this.restTemplate = restTemplate;
    }

    @Override
    public ApiResponseWrapper postRequest(String url, Object requestBody) throws JsonProcessingException {
        log.info("POST request to " + url);
        HttpEntity<Object> requestEntity = new HttpEntity<>(objectMapper.writeValueAsString(requestBody));
        ApiResponseWrapper apiResponseWrapper;
        URI uri = UriComponentsBuilder.fromUri(URI.create(url)).build().toUri();
        try {
            log.info("request -> " + requestBody);
            log.info("POST request to " + uri);
            ResponseEntity<String> apiResponse =
                restTemplate.exchange(uri, HttpMethod.POST, requestEntity, String.class);
            log.info("Receieved data ->>> From postRequest Endpoint {}", apiResponse.getStatusCode());
            if (apiResponse.getBody() !=null) {
                if(isErrorResponse(apiResponse)){
                    log.error("Received error response from endpoint");
                    apiResponseWrapper =
                        new ApiResponseWrapper(null, getErrorResponse(apiResponse, objectMapper)); //add error resp to resp entity
                } else {
                    apiResponseWrapper = new ApiResponseWrapper(apiResponse, null); //api response no error
                }
            } else {
                apiResponseWrapper =
                    new ApiResponseWrapper(null, new ErrorResponse(FAILURE, REQUEST_FAILED));
            }
        } catch (Exception e) {
            log.error("Error in postRequest", e);
            throw new ApiGlobalException(SOMETHING_WENT_WRONG,
            HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return apiResponseWrapper;
    }

    @Override
    public ApiResponseWrapper getRequest(String url) {
        return null;
    }

    @Override
    public ApiResponseWrapper deleteRequest(String url, Object requestBody) {
        return null;
    }

    @Override
    public ApiResponseWrapper getRequestById(String url, Object requestBody) {
        return null;
    }

    private ErrorResponse getErrorResponse(ResponseEntity<String> apiResponse, ObjectMapper objectMapper) {
        return Optional.ofNullable(apiResponse)
            .map(ResponseEntity<String>::getBody)
            .filter(Objects::nonNull)
            .map(
                body -> {
                    try {
                        return objectMapper.readValue(apiResponse.getBody(), ErrorResponse.class);
                    } catch (JsonProcessingException e) {
                        e.getMessage();
                        throw new ApiGlobalException(SOMETHING_WENT_WRONG,
                        HttpStatus.INTERNAL_SERVER_ERROR);
                    }
                })
            .orElse(null);
    }

    private boolean isErrorResponse(ResponseEntity<String> apiResponse) {
        log.info("Check if received error response from endpoint");
        return Optional.ofNullable(getErrorResponse(apiResponse, objectMapper))
            .map(ErrorResponse::getErrorMessage)
            .isPresent();
    }
}
