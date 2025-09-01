package uk.gov.hmcts.reform.dev.models.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@JsonInclude
public class CaseRequest {
    private String id;
    private String title;
    private String description;
    private String status;
    private LocalDateTime dueDate;
}
