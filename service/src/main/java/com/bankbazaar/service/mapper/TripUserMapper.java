package com.bankbazaar.service.mapper;

import com.bankbazaar.core.model.TripEntity;
import com.bankbazaar.core.model.TripUserMapEntity;
import com.bankbazaar.core.model.UserEntity;
import com.bankbazaar.dto.model.TripUserMapDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface TripUserMapper {

    @Mapping(target="tripId",source="tripId",qualifiedByName = "tripIdMapper")
    @Mapping(target="userId",source="userId",qualifiedByName = "userIdMapper")
    TripUserMapEntity dtoToDomain(TripUserMapDto dto);

    @Named("userIdMapper")
    public static UserEntity mapUserId(Long userId)
    {
        UserEntity user = new UserEntity();
        user.setUserId(userId);
        return user;
    }
    @Named("tripIdMapper")
    public static TripEntity mapTripId(Long tripId)
    {
        TripEntity trip = new TripEntity();
        trip.setTripId(tripId);
        return trip;
    }

}
