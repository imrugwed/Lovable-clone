package com.rocks.lovable_clone.mapper;

import com.rocks.lovable_clone.dto.member.AcceptRequest;
import com.rocks.lovable_clone.dto.member.AcceptResponse;
import com.rocks.lovable_clone.dto.member.MemberResponse;
import com.rocks.lovable_clone.entity.ProjectMember;
import com.rocks.lovable_clone.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProjectMemberMapper {

    @Mapping(target = "userId", source = "id")
    @Mapping(target = "role", constant = "OWNER")
    MemberResponse toProjectMemberResponseFromOwner(User owner);

    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "username", source = "user.username")
    @Mapping(target = "name", source = "user.name")
    @Mapping(target = "role", source = "projectRole")
    MemberResponse toProjectMemberResponseFromMember(ProjectMember projectMember);

    @Mapping(target = "msg",
            expression = "java( acceptRequest.acceptedRequest() ? \"Invitation Accepted\" : \"Invitation Denied\" )")
    AcceptResponse toAcceptedResponse(AcceptRequest acceptRequest);

}
