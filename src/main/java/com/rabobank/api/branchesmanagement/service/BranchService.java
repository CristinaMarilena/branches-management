package com.rabobank.api.branchesmanagement.service;

import com.rabobank.api.branchesmanagement.dto.GetBranchResponse;
import com.rabobank.api.branchesmanagement.dto.OpenBranchRequest;
import com.rabobank.api.branchesmanagement.dto.OpenBranchResponse;
import com.rabobank.api.branchesmanagement.entity.Branch;
import com.rabobank.api.branchesmanagement.mapper.CreateBranchMapper;
import com.rabobank.api.branchesmanagement.mapper.GetBranchMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BranchService {

    public static List<Branch> branchesStorage = new ArrayList<>();

    @Autowired
    private CreateBranchMapper createBranchMapper;

    @Autowired
    private GetBranchMapper getBranchMapper;

    public Optional<GetBranchResponse> getBranchById(Long branchId) {
        // Find the branch in the storage based on the ID
        return branchesStorage.stream()
                .filter(branch -> branch.getId().equals(branchId))
                .findFirst()
                .map(getBranchMapper::branchToCreateBranchResponse);
    }

    public OpenBranchResponse createBranch(OpenBranchRequest branchRequest) {

        // Create a new Branch entity and add it to the list
        Branch branch = createBranchMapper.createBranchRequestToBranch(branchRequest);

        // Add logic to generate branch code or handle uniqueness
        branchesStorage.add(branch);

        // Convert and return BranchResponse
        return createBranchMapper.branchToCreateBranchResponse(branch);
    }
}
