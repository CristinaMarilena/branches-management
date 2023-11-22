package com.rabobank.api.branchesmanagement.controller;

import com.rabobank.api.branchesmanagement.dto.OpenBranchRequest;
import com.rabobank.api.branchesmanagement.dto.OpenBranchResponse;
import com.rabobank.api.branchesmanagement.service.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/branches")
public class BranchesController {

    private final BranchService branchService;

    @Autowired
    public BranchesController(BranchService branchService) {
        this.branchService = branchService;
    }

    @PostMapping
    public ResponseEntity<OpenBranchResponse> openBranch(@RequestBody OpenBranchRequest branchRequest) {
        OpenBranchResponse createdBranch = branchService.createBranch(branchRequest);
        return new ResponseEntity<>(createdBranch, HttpStatus.CREATED);
    }
}
