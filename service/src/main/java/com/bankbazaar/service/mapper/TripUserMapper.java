package com.bankbazaar.service.mapper;

import com.bankbazaar.core.model.TripUserMapEntity;
import com.bankbazaar.dto.model.TripUserMapDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TripUserMapper {

    TripUserMapEntity dtoToDomain(TripUserMapDto dto);

    TripUserMapDto domainToDto(TripUserMapEntity entity);

}
