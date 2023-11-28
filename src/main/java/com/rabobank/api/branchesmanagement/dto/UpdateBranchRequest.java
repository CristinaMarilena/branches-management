package com.rabobank.api.branchesmanagement.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public record UpdateBranchRequest(@NotBlank String code,
                                  @NotBlank String name,
                                  @NotBlank String address,
                                  @Pattern(regexp = "^\\d{10}$")
                                String phone) {
}