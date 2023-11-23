package com.rabobank.api.branchesmanagement.controller;

import com.rabobank.api.branchesmanagement.dto.OpenBranchRequest;
import com.rabobank.api.branchesmanagement.dto.OpenBranchResponse;
import com.rabobank.api.branchesmanagement.service.BranchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/branches")
@RequiredArgsConstructor
@Slf4j
public class BranchesController {
    private final BranchService branchService;

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<OpenBranchResponse> openBranch(@RequestBody @Valid OpenBranchRequest request) {
        log.info("Open a new branch [{}]", request);

        val response = branchService.openBranch(request);

        return getResponseEntityWithCreated(response);
    }

    private ResponseEntity<OpenBranchResponse> getResponseEntityWithCreated(final OpenBranchResponse response) {
        return ResponseEntity.created(getLocation(response.id()))
                             .body(response);
    }

    private URI getLocation(final String branchId) {
        return ServletUriComponentsBuilder.fromCurrentRequestUri()
                                          .path("/{id}")
                                          .buildAndExpand(branchId)
                                          .toUri();
    }
}