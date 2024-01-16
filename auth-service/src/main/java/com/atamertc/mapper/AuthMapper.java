package com.atamertc.mapper;

import com.atamertc.dto.request.RegisterRequestDto;
import com.atamertc.dto.request.UserSaveRequestDto;
import com.atamertc.dto.response.TokenResponseDto;
import com.atamertc.rabbitmq.model.RegisterUserModel;
import com.atamertc.repository.entity.Auth;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AuthMapper {
    AuthMapper INSTANCE = Mappers.getMapper(AuthMapper.class);

    Auth toAuth(final RegisterRequestDto dto);

    @Mapping( target = "authid", source = "id")
    UserSaveRequestDto toUserSaveRequestDto(final Auth auth);

    @Mapping( target = "authid", source = "id")
    RegisterUserModel toRegisterUserModel(final Auth auth);
}
