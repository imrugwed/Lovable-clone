package com.rocks.lovable_clone.dto.auth;

public record AuthResponse(
        String token,
        UserProfileResponse user
) {

}
