package com.rabobank.api.branchesmanagement.controller;

import com.rabobank.api.branchesmanagement.dto.GetBranchResponse;
import com.rabobank.api.branchesmanagement.dto.OpenBranchRequest;
import com.rabobank.api.branchesmanagement.dto.OpenBranchResponse;
import com.rabobank.api.branchesmanagement.service.BranchService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/branches")
public class BranchesController {

    private final BranchService branchService;

    @Autowired
    public BranchesController(BranchService branchService) {
        this.branchService = branchService;
    }

    @GetMapping(value = "/{branchId}", produces = "application/json")
    public ResponseEntity<GetBranchResponse> getBranch(@PathVariable Long branchId) {
        Optional<GetBranchResponse> branchResponse = branchService.getBranchById(branchId);
        return branchResponse
                .map(response -> new ResponseEntity<>(response, OK))
                .orElseGet(() -> new ResponseEntity<>(NOT_FOUND));
    }


    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<OpenBranchResponse> openBranch(@RequestBody @Valid OpenBranchRequest branchRequest) {
        OpenBranchResponse createdBranch = branchService.createBranch(branchRequest);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(createLocationUri(createdBranch.getId()));

        return new ResponseEntity<>(createdBranch, headers, CREATED);
    }

    private URI createLocationUri(Object resourceId) {
        return ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(resourceId)
                .toUri();
    }
}
