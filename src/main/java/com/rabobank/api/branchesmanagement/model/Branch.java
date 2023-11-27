package com.rabobank.api.branchesmanagement.model;

import lombok.Data;

@Data
public class Branch {
    private String id;

    private String code;

    private String name;

    private String address;

    private String phone;
}