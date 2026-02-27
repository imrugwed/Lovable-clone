package com.rocks.lovable_clone.service;

import com.rocks.lovable_clone.dto.member.*;

import java.util.List;

public interface ProjectMemberService {
    List<MemberResponse> getProjectMembers(Long projectId);

    MemberResponse inviteMember(Long projectId, InviteMemberRequest request);

    MemberResponse updateMemberRole(Long projectId, Long memberId, UpdateMemberRoleRequest request);

    void removeProjectMember(Long projectId, Long memberId);

    AcceptResponse inviteRequestAcceptedOrNot(AcceptRequest acceptRequest);

}
