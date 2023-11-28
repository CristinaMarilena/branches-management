package com.rabobank.api.branchesmanagement.service;

import com.rabobank.api.branchesmanagement.mapper.BranchMapper;
import com.rabobank.api.branchesmanagement.model.Branch;
import com.rabobank.api.branchesmanagement.repository.BranchRepository;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

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

    public void deleteBranch(final String id) {
        branchRepository.deleteById(id);
    }

    public Branch getBranch(final String id) {
        val branchDocument = branchRepository.findById(id)
                                             .orElseThrow(() -> new NoSuchElementException("No such branch by id " + id));

        return branchMapper.mapToBranch(branchDocument);
    }

    public List<Branch> getAllBranches() {
        return branchRepository.findAll()
                               .stream()
                               .map(branchMapper::mapToBranch)
                               .toList();
    }
}