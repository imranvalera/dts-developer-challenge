package uk.gov.hmcts.reform.dev.models.reponse;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ErrorResponse {

    public ErrorResponse(String status, String errorMessage) {
        this.status = status;
        this.errorMessage = errorMessage;
    }

    @JsonProperty("status")
    private String status;

    @JsonProperty("errorMessage")
    private String errorMessage;
}
