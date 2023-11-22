package com.rabobank.api.branchesmanagement.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Branch {
    private String code;
    private String name;
    private String address;
    private String phone;

    // getters and setters
}
