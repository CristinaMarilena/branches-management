package com.rabobank.api.branchesmanagement.dto;

public record OpenBranchResponse(String id,
                                 String code,
                                 String name,
                                 String address,
                                 String phone,
                                 String url) {
}