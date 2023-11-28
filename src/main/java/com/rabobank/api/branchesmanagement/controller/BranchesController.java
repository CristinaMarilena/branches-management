package com.rabobank.api.branchesmanagement.controller;

import com.rabobank.api.branchesmanagement.dto.*;
import com.rabobank.api.branchesmanagement.service.BranchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

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

    @DeleteMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Void> closeBranch(@PathVariable("id") String id) {
        log.info("Close a given branch [{}]", id);

        branchService.closeBranch(id);

        return ResponseEntity.noContent()
                             .build();
    }

    @GetMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<GetBranchResponse> getBranch(@PathVariable("id") String id) {
        log.info("Get branch [{}]", id);

        val response = branchService.getBranch(id);

        return ResponseEntity.ok()
                             .body(response);
    }

    @GetMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<List<GetBranchResponse>> getAllBranches() {
        log.info("Get all branches");

        val response = branchService.getAllBranches();

        return ResponseEntity.ok()
                             .body(response);
    }

    @PutMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<UpdateBranchResponse> updateBranch(@PathVariable("id") String id,
                                                             @RequestBody @Valid UpdateBranchRequest request) {
        log.info("Update branch [{}] with data [{}]", id, request);

        val response = branchService.updateBranch(id, request);

        return ResponseEntity.ok()
                .body(response);
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