package uk.gov.hmcts.reform.dev.gateway;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import uk.gov.hmcts.reform.dev.models.reponse.ErrorResponse;


@Data
@AllArgsConstructor
public class ApiResponseWrapper {
    private ResponseEntity<String> response;
    private ErrorResponse error;
}
