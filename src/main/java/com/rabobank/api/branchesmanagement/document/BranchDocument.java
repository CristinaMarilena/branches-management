package com.rabobank.api.branchesmanagement.document;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document("branches")
public class BranchDocument {
    @Id
    private String id;

    private String code;

    private String name;

    private String address;

    private String phone;
}