package com.spring.delivery.mapper;

import com.spring.delivery.domain.request.RequestRegister;
import com.spring.delivery.domain.response.ResponseAuthentication;
import com.spring.delivery.domain.response.ResponseUpdateInformation;
import com.spring.delivery.model.JwtPayload;
import com.spring.delivery.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User toUser(RequestRegister userRequest);

    ResponseAuthentication.UserDTO toUserDTO(User user);

    JwtPayload.UserPayload toUserPayload(User user);

    ResponseUpdateInformation toResponseUpdateInformation(User user);

}
