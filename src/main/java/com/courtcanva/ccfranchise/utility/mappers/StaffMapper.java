package com.courtcanva.ccfranchise.utility.mappers;

import com.courtcanva.ccfranchise.dtos.StaffDto;
import com.courtcanva.ccfranchise.model.Staff;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface StaffMapper {

    @Mapping(target = "id",ignore = true)
    @Mapping(target = "firstName",source = "firstName")
    @Mapping(target = "lastName",source = "lastName")
    @Mapping(target = "email",source = "email")
    @Mapping(target = "phoneNumber",source = "phoneNumber")
    @Mapping(target = "address",source = "address")
    @Mapping(target = "postcode",source = "postcode")
    @Mapping(target = "state",source = "state")
    @Mapping(target = "password",source = "password")
    @Mapping(target = "verificationDocumentLink",source = "verificationDocumentLink")
    Staff toStaffEntity(StaffDto staffDto);

    StaffDto toStaffDto(Staff staff);
}
