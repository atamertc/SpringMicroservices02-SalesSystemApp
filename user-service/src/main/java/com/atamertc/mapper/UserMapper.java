package com.atamertc.mapper;

import com.atamertc.dto.request.UserSaveRequestDto;
import com.atamertc.rabbitmq.model.RegisterUserModel;
import com.atamertc.repository.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User toUser(final UserSaveRequestDto dto);


    User toUser(final RegisterUserModel model);
}
