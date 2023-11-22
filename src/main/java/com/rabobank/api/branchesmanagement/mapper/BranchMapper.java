package com.rabobank.api.branchesmanagement.mapper;

import com.rabobank.api.branchesmanagement.dto.OpenBranchRequest;
import com.rabobank.api.branchesmanagement.dto.OpenBranchResponse;
import com.rabobank.api.branchesmanagement.entity.Branch;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Service;

@Mapper
@Service
public interface BranchMapper {

    @Mapping(target = "url", ignore = true)
    OpenBranchResponse branchToCreateBranchResponse(Branch branch);

    Branch createBranchRequestToBranch(OpenBranchRequest request);
}
