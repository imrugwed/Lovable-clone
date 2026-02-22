package com.rocks.lovable_clone.dto.member;

import com.rocks.lovable_clone.enums.ProjectRole;

public record InviteMemberRequest(
        String email,
        ProjectRole role
) {
}
