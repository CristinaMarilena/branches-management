package com.rabobank.api.branchesmanagement.controller;

import com.rabobank.api.branchesmanagement.dto.GetBranchResponse;
import com.rabobank.api.branchesmanagement.dto.OpenBranchRequest;
import com.rabobank.api.branchesmanagement.dto.UpdateBranchRequest;
import com.rabobank.api.branchesmanagement.service.BranchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
    public ResponseEntity<Void> openBranch(@RequestBody @Valid OpenBranchRequest request) {
        log.info("Open a new branch [{}]", request);

        val id = branchService.openBranch(request);

        return getResponseEntityWithCreated(id);
    }

    @DeleteMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Void> closeBranch(@PathVariable("id") String id) {
        log.info("Close given branch [{}]", id);

        branchService.closeBranch(id);

        return ResponseEntity.noContent()
                             .build();
    }

    @GetMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<GetBranchResponse> getBranch(@PathVariable("id") String id) {
        log.info("Get branch [{}]", id);

        val response = branchService.getBranch(id);

        return ResponseEntity.ok(response);
    }

    @GetMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<List<GetBranchResponse>> getAllBranches() {
        log.info("Get all branches");

        val response = branchService.getAllBranches();

        return ResponseEntity.ok(response);
    }

    @PutMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Void> updateBranch(@PathVariable("id") String id,
                                             @RequestBody @Valid UpdateBranchRequest request) {
        log.info("Update branch [{}] with data [{}]", id, request);

        branchService.updateBranch(id, request);

        return ResponseEntity.noContent()
                             .build();
    }

    private ResponseEntity<Void> getResponseEntityWithCreated(final String id) {
        return ResponseEntity.created(getLocation(id))
                             .build();
    }

    private URI getLocation(final String branchId) {
        return ServletUriComponentsBuilder.fromCurrentRequestUri()
                                          .path("/{id}")
                                          .buildAndExpand(branchId)
                                          .toUri();
    }
}