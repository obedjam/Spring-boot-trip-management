package com.bankbazaar.service.mapper;

import com.bankbazaar.core.model.TripEntity;
import com.bankbazaar.dto.model.TripDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TripMapper {
    TripEntity dtoToDomain(TripDto dto);
    TripDto domainToDto(TripEntity entity);

}
