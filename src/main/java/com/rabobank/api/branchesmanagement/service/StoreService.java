package com.rabobank.api.branchesmanagement.service;

import com.rabobank.api.branchesmanagement.mapper.BranchMapper;
import com.rabobank.api.branchesmanagement.model.Branch;
import com.rabobank.api.branchesmanagement.repository.BranchRepository;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StoreService {
    private final BranchMapper branchMapper;
    private final BranchRepository branchRepository;

    public Branch saveBranch(final Branch branch) {
        val branchDocument = branchMapper.mapToBranchDocument(branch);

        val savedBranchDocument = branchRepository.save(branchDocument);

        return branchMapper.mapToBranch(savedBranchDocument);
    }
}