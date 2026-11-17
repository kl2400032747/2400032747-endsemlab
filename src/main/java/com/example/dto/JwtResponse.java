
package com.example.dto;

import java.util.List;

public record JwtResponse(
    String message,
    String username,
    List<String> roles
) {
}
