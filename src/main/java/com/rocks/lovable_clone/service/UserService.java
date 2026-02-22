package com.rocks.lovable_clone.service;

import com.rocks.lovable_clone.dto.auth.UserProfileResponse;

public interface UserService {
    UserProfileResponse getProfile(Long userId);
}
