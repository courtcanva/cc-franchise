package com.courtcanva.ccfranchise.mappers;

import com.courtcanva.ccfranchise.dtos.FranchiseeGetDto;
import com.courtcanva.ccfranchise.dtos.FranchiseePostDto;
import com.courtcanva.ccfranchise.models.Franchisee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;


@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface FranchiseeMapper {

    Franchisee postDtoToFranchisee(FranchiseePostDto franchiseePostDto);

    @Mapping(target = "franchiseeId", source = "id")
    FranchiseeGetDto franchiseeToGetDto(Franchisee franchisee);

}
