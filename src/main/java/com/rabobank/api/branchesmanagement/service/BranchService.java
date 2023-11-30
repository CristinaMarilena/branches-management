package com.rabobank.api.branchesmanagement.service;

import com.rabobank.api.branchesmanagement.dto.GetBranchResponse;
import com.rabobank.api.branchesmanagement.dto.OpenBranchRequest;
import com.rabobank.api.branchesmanagement.dto.UpdateBranchRequest;
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

    public String openBranch(final OpenBranchRequest request) {
        val branch = branchMapper.mapToBranch(request);

        val savedBranch = storeService.saveBranch(branch);

        return savedBranch.getId();
    }

    public void closeBranch(final String id) {
        storeService.deleteBranch(id);
    }

    public void updateBranch(final String id, final UpdateBranchRequest request) {
        val branch = storeService.getBranch(id);

        branchMapper.updateBranch(branch, request);

        storeService.saveBranch(branch);
    }

    public GetBranchResponse getBranch(final String id) {
        val branch = storeService.getBranch(id);

        return branchMapper.mapToGetBranchResponse(branch);
    }

    public List<GetBranchResponse> getAllBranches() {
        return storeService.getAllBranches()
                           .stream()
                           .map(branchMapper::mapToGetBranchResponse)
                           .toList();
    }
}