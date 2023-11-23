package com.rabobank.api.branchesmanagement.service;

import com.rabobank.api.branchesmanagement.dto.OpenBranchRequest;
import com.rabobank.api.branchesmanagement.dto.OpenBranchResponse;
import com.rabobank.api.branchesmanagement.mapper.BranchMapper;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

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
}