
package com.example.dto;

import jakarta.validation.constraints.NotBlank;

public record JwtRequest(
    @NotBlank(message = "Token is required")
    String token
) {
}
