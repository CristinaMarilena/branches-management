package com.rabobank.api.branchesmanagement.service;

import com.rabobank.api.branchesmanagement.dto.*;
import com.rabobank.api.branchesmanagement.mapper.BranchMapper;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BranchService {

    private final BranchMapper branchMapper;
    private final StoreService storeService;

    public OpenBranchResponse openBranch(final OpenBranchRequest request) {
        val branch = branchMapper.mapToBranch(request);

        val savedBranch = storeService.saveBranch(branch);

        return branchMapper.mapToOpenBranchResponse(savedBranch);
    }

    public void closeBranch(final String id) {
        storeService.deleteBranch(id);
    }

    public GetBranchResponse getBranch(final String id) {
        val branch = storeService.getBranch(id);
        return branchMapper.mapToGetBranchResponse(branch);
    }

    public UpdateBranchResponse updateBranch(final String id, final UpdateBranchRequest request) {
        val existingBranch = storeService.getBranch(id);

        branchMapper.updateBranchFromRequest(existingBranch, request);

        val updatedBranch = storeService.saveBranch(existingBranch);

        return branchMapper.mapToUpdateBranchResponse(updatedBranch);
    }

    public List<GetBranchResponse> getAllBranches() {
        return storeService.getAllBranches()
                           .stream()
                           .map(branchMapper::mapToGetBranchResponse)
                           .toList();
    }
}