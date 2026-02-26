package com.rocks.lovable_clone.dto.member;

import com.rocks.lovable_clone.enums.ProjectRole;

import jakarta.validation.constraints.NotNull;

public record UpdateMemberRoleRequest(
        @NotNull ProjectRole role) {
}
