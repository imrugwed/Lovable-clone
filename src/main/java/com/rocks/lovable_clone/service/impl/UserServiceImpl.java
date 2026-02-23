package com.rocks.lovable_clone.service.impl;

import com.rocks.lovable_clone.dto.auth.UserProfileResponse;
import com.rocks.lovable_clone.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public UserProfileResponse getProfile(Long userId) {
        return null;
    }
}
