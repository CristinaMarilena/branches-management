package com.rabobank.api.branchesmanagement.controller;

import com.rabobank.api.branchesmanagement.dto.OpenBranchRequest;
import com.rabobank.api.branchesmanagement.dto.OpenBranchResponse;
import com.rabobank.api.branchesmanagement.service.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/branches")
public class BranchesController {

    private final BranchService branchService;

    @Autowired
    public BranchesController(BranchService branchService) {
        this.branchService = branchService;
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<OpenBranchResponse> openBranch(@RequestBody OpenBranchRequest branchRequest) {
        OpenBranchResponse createdBranch = branchService.createBranch(branchRequest);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(createLocationUri(createdBranch.getId()));

        return new ResponseEntity<>(createdBranch, headers, HttpStatus.CREATED);
    }

    private URI createLocationUri(Object resourceId) {
        return ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(resourceId)
                .toUri();
    }
}
