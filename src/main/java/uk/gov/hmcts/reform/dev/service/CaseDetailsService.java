package uk.gov.hmcts.reform.dev.service;

import uk.gov.hmcts.reform.dev.models.reponse.CaseResponse;
import uk.gov.hmcts.reform.dev.models.request.CaseRequest;

public interface CaseDetailsService {
    CaseResponse fetchCaseDetailsById(String id);
    CaseResponse fetchAllCaseDetails();
    CaseResponse createCase(CaseRequest caseRequest);
    CaseResponse updateCaseStatus(String id, String status);
    CaseResponse deleteCase(String id);
}
