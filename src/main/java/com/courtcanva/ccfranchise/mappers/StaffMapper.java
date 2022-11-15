package com.courtcanva.ccfranchise.mappers;

import com.courtcanva.ccfranchise.dtos.StaffGetDto;
import com.courtcanva.ccfranchise.dtos.StaffPostDto;
import com.courtcanva.ccfranchise.models.Staff;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;


@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface StaffMapper {

    Staff postDtoToStaff(StaffPostDto staffPostDto);

    @Mapping(target = "staffId", source = "id")
    StaffGetDto staffToGetDto(Staff staff);
}
