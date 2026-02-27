package com.rocks.lovable_clone.service.impl;

import com.rocks.lovable_clone.dto.member.*;
import com.rocks.lovable_clone.entity.Project;
import com.rocks.lovable_clone.entity.ProjectMember;
import com.rocks.lovable_clone.entity.ProjectMemberId;
import com.rocks.lovable_clone.entity.User;
import com.rocks.lovable_clone.mapper.ProjectMemberMapper;
import com.rocks.lovable_clone.repository.ProjectMemberRepository;
import com.rocks.lovable_clone.repository.ProjectRepository;
import com.rocks.lovable_clone.repository.UserRepository;
import com.rocks.lovable_clone.security.AuthUtil;
import com.rocks.lovable_clone.service.ProjectMemberService;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@Transactional
public class ProjectMemberServiceImpl implements ProjectMemberService {

    ProjectMemberRepository projectMemberRepository;
    ProjectRepository projectRepository;
    ProjectMemberMapper projectMemberMapper;
    UserRepository userRepository;
    AuthUtil authUtil;


    @Override
    public List<MemberResponse> getProjectMembers(Long projectId) {
        Long userId = authUtil.getCurrentUserId();
        Project project = getAccessibleProjectById(projectId, userId);

        return projectMemberRepository.findByIdProjectId(projectId)
                .stream()
                .map(projectMemberMapper::toProjectMemberResponseFromMember)
                .toList();
    }

    @Override
    public MemberResponse inviteMember(Long projectId, InviteMemberRequest request) {
        Long userId = authUtil.getCurrentUserId();
        Project project = getAccessibleProjectById(projectId, userId);

        User invitee = userRepository.findByUsername(request.username()).orElseThrow();

        if (invitee.getId().equals(userId)) {
            throw new RuntimeException("Cannot invite yourself");
        }

        ProjectMemberId projectMemberId = new ProjectMemberId(projectId, invitee.getId());

        if (projectMemberRepository.existsById(projectMemberId)) {
            throw new RuntimeException("Cannot invite once again");
        }

        ProjectMember member = ProjectMember.builder()
                .id(projectMemberId)
                .project(project)
                .user(invitee)
                .projectRole(request.role())
                .invitedAt(Instant.now())
                .build();

        projectMemberRepository.save(member);

        return projectMemberMapper.toProjectMemberResponseFromMember(member);
    }

    @Override
    public MemberResponse updateMemberRole(Long projectId, Long memberId, UpdateMemberRoleRequest request) {
        Long userId = authUtil.getCurrentUserId();
        Project project = getAccessibleProjectById(projectId, userId);

        ProjectMemberId projectMemberId = new ProjectMemberId(projectId, memberId);
        ProjectMember projectMember = projectMemberRepository.findById(projectMemberId).orElseThrow();

        projectMember.setProjectRole(request.role());

        projectMemberRepository.save(projectMember);

        return projectMemberMapper.toProjectMemberResponseFromMember(projectMember);
    }

    @Override
    public void removeProjectMember(Long projectId, Long memberId) {
        Long userId = authUtil.getCurrentUserId();
        Project project = getAccessibleProjectById(projectId, userId);

        ProjectMemberId projectMemberId = new ProjectMemberId(projectId, memberId);
        if (!projectMemberRepository.existsById(projectMemberId)) {
            throw new RuntimeException("Member not found in project");
        }

        projectMemberRepository.deleteById(projectMemberId);
    }

    public AcceptResponse inviteRequestAcceptedOrNot(AcceptRequest acceptRequest) {
        User user = userRepository.findById(acceptRequest.id())
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + acceptRequest.id()));

        ProjectMember projectMember = projectMemberRepository.findByUser(user)
                .orElseThrow(() -> new IllegalStateException("Project invitation not found for user id: " + user.getId()));

        ProjectMemberId projectMemberId = new ProjectMemberId(projectMember.getId().getProjectId(), user.getId());

        // If request is rejected
        if (Boolean.FALSE.equals(acceptRequest.acceptedRequest())) {
            projectMemberRepository.deleteById(projectMemberId);
            return projectMemberMapper.toAcceptedResponse(acceptRequest);
        }

        // If request is accepted
        projectMember.setAcceptedAt(Instant.now());
        projectMemberRepository.save(projectMember);
        return projectMemberMapper.toAcceptedResponse(acceptRequest);
    }

    ///  INTERNAL FUNCTIONS

    public Project getAccessibleProjectById(Long projectId, Long userId) {
        return projectRepository.findAccessibleProjectById(projectId, userId).orElseThrow();
    }
}
