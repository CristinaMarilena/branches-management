package com.rabobank.api.branchesmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OpenBranchRequest {
    private String name;
    private String address;
    private String phone;
}
