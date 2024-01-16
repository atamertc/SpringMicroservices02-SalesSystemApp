package com.atamertc.mapper;


import com.atamertc.dto.request.AddUserRequestDto;
import com.atamertc.repository.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "userid", source = "id")
    User toUser(final AddUserRequestDto dto);
}
