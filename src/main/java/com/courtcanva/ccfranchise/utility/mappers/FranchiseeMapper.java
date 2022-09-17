package com.courtcanva.ccfranchise.utility.mappers;

import com.courtcanva.ccfranchise.dtos.FranchiseeDto;
import com.courtcanva.ccfranchise.model.Franchisee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;
@Component
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface FranchiseeMapper {

    @Mapping(target = "id",ignore = true)
    Franchisee toFranchiseeEntity(FranchiseeDto staffDto);

    FranchiseeDto toFranchiseeDto(Franchisee staff);
}
