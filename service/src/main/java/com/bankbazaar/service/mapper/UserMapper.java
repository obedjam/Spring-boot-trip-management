package com.bankbazaar.service.mapper;

import com.bankbazaar.core.model.UserEntity;
import com.bankbazaar.dto.model.UserDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserEntity dtoToDomain(UserDto dto);
    UserDto domainToDto(UserEntity entity);
}
