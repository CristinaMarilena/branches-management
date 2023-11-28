package com.rabobank.api.branchesmanagement.mapper;

import com.rabobank.api.branchesmanagement.document.BranchDocument;
import com.rabobank.api.branchesmanagement.dto.*;
import com.rabobank.api.branchesmanagement.model.Branch;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BranchMapper {

    Branch mapToBranch(OpenBranchRequest openBranchRequest);

    void updateBranchFromRequest(@MappingTarget Branch branch, UpdateBranchRequest request);

    @Mapping(target = "url", ignore = true)
    OpenBranchResponse mapToOpenBranchResponse(Branch branch);

    BranchDocument mapToBranchDocument(Branch branch);

    Branch mapToBranch(BranchDocument branchDocument);

    GetBranchResponse mapToGetBranchResponse(Branch branch);

    UpdateBranchResponse mapToUpdateBranchResponse(Branch branch);
}