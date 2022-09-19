package com.courtcanva.ccfranchise.mappers;

import com.courtcanva.ccfranchise.dtos.FranchiseeAndStaffInfoDto;
import com.courtcanva.ccfranchise.models.Franchisee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface FranchiseeMapper {

    @Mapping(target = "id", ignore = true)
    Franchisee toFranchiseeEntity(FranchiseeAndStaffInfoDto franchiseeInfoDto);

}
