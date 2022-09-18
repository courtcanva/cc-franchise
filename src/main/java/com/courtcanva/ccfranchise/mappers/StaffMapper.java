package com.courtcanva.ccfranchise.mappers;

import com.courtcanva.ccfranchise.dtos.StaffInfoDto;
import com.courtcanva.ccfranchise.model.Staff;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface StaffMapper {

    @Mapping(target = "id",ignore = true)
    Staff toStaffEntity(StaffInfoDto staffInfoDto);

    StaffInfoDto toStaffDto(Staff staff);
}
