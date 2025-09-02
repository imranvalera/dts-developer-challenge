package uk.gov.hmcts.reform.dev.models.reponse;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@JsonInclude
public class CaseResponse {
    private int id;
    private String title;
    private String caseNumber;
    private String description;
    private String status;
    private LocalDateTime createdDate;
    private LocalDateTime dueDate;
}
