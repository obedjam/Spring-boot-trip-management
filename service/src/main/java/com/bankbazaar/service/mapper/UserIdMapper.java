package com.bankbazaar.service.mapper;

import com.bankbazaar.core.model.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
@Mapper
public interface UserIdMapper {
    @Named("userIdMapper")
    public static UserEntity mapTripId(Long userId)
    {
        UserEntity user = new UserEntity();
        user.setUserId(userId);
        return user;
    }
}
