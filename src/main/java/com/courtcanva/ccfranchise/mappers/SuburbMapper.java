package com.courtcanva.ccfranchise.mappers;

import com.courtcanva.ccfranchise.dtos.suburbs.SuburbGetDto;
import com.courtcanva.ccfranchise.dtos.suburbs.SuburbPostDto;
import com.courtcanva.ccfranchise.models.Suburb;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SuburbMapper {

    Suburb postDtoToSuburb(SuburbPostDto suburbPostDto);

    SuburbGetDto suburbToGetDto(Suburb suburb);
}
