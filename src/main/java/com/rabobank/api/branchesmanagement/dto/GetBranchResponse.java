package com.rabobank.api.branchesmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetBranchResponse {
    private long id;
    private String name;
    private String address;
    private String phone;
}
