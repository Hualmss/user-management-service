package com.pragma.powerup.application.mapper;

import com.pragma.powerup.application.dto.UserRequest;
import com.pragma.powerup.application.dto.UserResponse;
import com.pragma.powerup.domain.model.User;
import com.pragma.powerup.domain.util.constans.EnumRol;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        imports = { EnumRol.class },
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    User toUser(UserRequest userRequest);

    @Mapping(
            target = "rol",
            expression = "java(EnumRol.fromId(user.getRol()).getName())"
    )
    UserResponse toUserResponse(User user);

}
