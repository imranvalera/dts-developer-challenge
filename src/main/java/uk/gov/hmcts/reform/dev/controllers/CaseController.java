package uk.gov.hmcts.reform.dev.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uk.gov.hmcts.reform.dev.models.reponse.CaseResponse;
import uk.gov.hmcts.reform.dev.service.CaseDetailsService;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/codingTest/case")
public class CaseController {

    private final CaseDetailsService caseDetailsService;

    @GetMapping(value = "/case/{id}", produces = "application/json")
    public CaseResponse getCaseResponseById(@PathVariable("id") String id) {
        log.info(
            "Request Received ---> fetching case details; "
        );
        return caseDetailsService.fetchCaseDetailsById(id);
    }

}
