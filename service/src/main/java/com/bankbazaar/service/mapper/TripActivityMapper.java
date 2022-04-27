package com.bankbazaar.service.mapper;

import com.bankbazaar.core.model.TripActivityEntity;
import com.bankbazaar.dto.model.TripActivityDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TripActivityMapper {
    TripActivityEntity dtoToDomain(TripActivityDto dto);
    TripActivityDto domainToDto(TripActivityEntity entity);
}
