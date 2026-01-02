package com.pragma.powerup.application.mapper;

import com.pragma.powerup.application.dto.UserRequest;
import com.pragma.powerup.application.dto.UserResponse;
import com.pragma.powerup.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    User toUser(UserRequest userRequest);
    UserResponse toUserResponse(User user);

}
