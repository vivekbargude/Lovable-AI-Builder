package com.vivek.projects.lovable_clone.mapper;

import com.vivek.projects.lovable_clone.dto.auth.SignupRequest;
import com.vivek.projects.lovable_clone.dto.auth.UserProfileResponse;
import com.vivek.projects.lovable_clone.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toEntity(SignupRequest signupRequest);

    @Mapping(target = "username", source = "username")
    @Mapping(target = "name", source = "name")
    UserProfileResponse toUserProfileResponse(User user);
}
