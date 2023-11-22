package com.rabobank.api.branchesmanagement.service;

import com.rabobank.api.branchesmanagement.dto.OpenBranchRequest;
import com.rabobank.api.branchesmanagement.dto.OpenBranchResponse;
import com.rabobank.api.branchesmanagement.entity.Branch;
import com.rabobank.api.branchesmanagement.mapper.BranchMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BranchService {

    public static List<Branch> branchesStorage = new ArrayList<>();

    @Autowired
    private BranchMapper branchMapper;

    public OpenBranchResponse createBranch(OpenBranchRequest branchRequest) {

        // Create a new Branch entity and add it to the list
        Branch branch = branchMapper.createBranchRequestToBranch(branchRequest);

        // Add logic to generate branch code or handle uniqueness
        branchesStorage.add(branch);

        // Convert and return BranchResponse
        return branchMapper.branchToCreateBranchResponse(branch);
    }
}
