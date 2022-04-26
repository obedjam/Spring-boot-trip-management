package com.bankbazaar.service.mapper;

import com.bankbazaar.core.model.TripActivityEntity;
import com.bankbazaar.core.model.TripEntity;
import com.bankbazaar.dto.model.TripActivityDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface TripActivityMapper {
    @Mapping(target="tripId",source="tripId",qualifiedByName = "tripIdMapper")
    TripActivityEntity dtoToDomain(TripActivityDto dto);

    @Named("tripIdMapper")
    public static TripEntity mapTripId(Long tripId)
    {
        TripEntity trip = new TripEntity();
        trip.setTripId(tripId);
        return trip;
    }
}
