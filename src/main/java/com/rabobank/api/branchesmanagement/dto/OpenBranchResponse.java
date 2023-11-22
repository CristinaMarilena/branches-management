package com.rabobank.api.branchesmanagement.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OpenBranchResponse {
    private Long id;
    private String code;
    private String name;
    private String address;
    private String phone;
    private String url; // Add a URL reference
}
