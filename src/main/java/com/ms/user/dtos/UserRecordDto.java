package com.ms.user.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserRecordDto (@NotBlank String username,
                             @NotBlank @Email String email) {
}
