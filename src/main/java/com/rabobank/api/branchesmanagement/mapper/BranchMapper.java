package com.rabobank.api.branchesmanagement.mapper;

import com.rabobank.api.branchesmanagement.document.BranchDocument;
import com.rabobank.api.branchesmanagement.dto.GetBranchResponse;
import com.rabobank.api.branchesmanagement.dto.OpenBranchRequest;
import com.rabobank.api.branchesmanagement.dto.OpenBranchResponse;
import com.rabobank.api.branchesmanagement.model.Branch;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BranchMapper {
    Branch mapToBranch(OpenBranchRequest openBranchRequest);

    @Mapping(target = "url", ignore = true)
    OpenBranchResponse mapToOpenBranchResponse(Branch branch);

    BranchDocument mapToBranchDocument(Branch branch);

    Branch mapToBranch(BranchDocument branchDocument);

    GetBranchResponse mapToGetBranchResponse(Branch branch);
}