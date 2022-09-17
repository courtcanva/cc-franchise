package com.courtcanva.ccfranchise.utility.mappers;

import com.courtcanva.ccfranchise.dtos.FranchiseDto;
import com.courtcanva.ccfranchise.dtos.StaffDto;
import com.courtcanva.ccfranchise.model.Franchise;
import com.courtcanva.ccfranchise.model.Staff;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;
@Component
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface FranchiseeMapper {

    @Mapping(target = "id",ignore = true)
    Franchise toFranchiseeEntity(FranchiseDto staffDto);

    FranchiseDto toFranchiseeDto(Franchise staff);
}
