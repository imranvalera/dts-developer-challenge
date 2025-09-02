package uk.gov.hmcts.reform.dev.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import uk.gov.hmcts.reform.dev.gateway.APICallService;
import uk.gov.hmcts.reform.dev.models.reponse.CaseResponse;
import uk.gov.hmcts.reform.dev.models.request.CaseRequest;

@Service
@Slf4j
@RequiredArgsConstructor
public class CaseDetailsServiceImpl implements CaseDetailsService {
    private final APICallService apiCallService;
    private final ObjectMapper objectMapper;

    @Override
    public CaseResponse fetchCaseDetailsById(String id) {
        return null;
    }

    @Override
    public CaseResponse fetchAllCaseDetails() {
        return null;
    }

    @Override
    public CaseResponse createCase(CaseRequest caseRequest) {
        return null;
    }

    @Override
    public CaseResponse updateCaseStatus(String id, String status) {
        return null;
    }

    @Override
    public CaseResponse deleteCase(String id) {
        return null;
    }
}
