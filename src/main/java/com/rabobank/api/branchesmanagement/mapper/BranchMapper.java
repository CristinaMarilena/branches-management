package com.rabobank.api.branchesmanagement.mapper;

import com.rabobank.api.branchesmanagement.document.BranchDocument;
import com.rabobank.api.branchesmanagement.dto.GetBranchResponse;
import com.rabobank.api.branchesmanagement.dto.OpenBranchRequest;
import com.rabobank.api.branchesmanagement.dto.UpdateBranchRequest;
import com.rabobank.api.branchesmanagement.model.Branch;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BranchMapper {
    Branch mapToBranch(OpenBranchRequest request);

    void updateBranch(@MappingTarget Branch branch, UpdateBranchRequest request);

    GetBranchResponse mapToGetBranchResponse(Branch branch);

    BranchDocument mapToBranchDocument(Branch branch);

    Branch mapToBranch(BranchDocument branchDocument);
}