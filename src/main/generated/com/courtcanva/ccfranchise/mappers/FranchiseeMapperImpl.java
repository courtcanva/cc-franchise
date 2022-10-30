package com.courtcanva.ccfranchise.mappers;

import com.courtcanva.ccfranchise.dtos.FranchiseeGetDto;
import com.courtcanva.ccfranchise.dtos.FranchiseePostDto;
import com.courtcanva.ccfranchise.models.Franchisee;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-10-30T21:01:54+1000",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 17.0.4.1 (Amazon.com Inc.)"
)
@Component
public class FranchiseeMapperImpl implements FranchiseeMapper {

    @Override
    public Franchisee postDtoToFranchisee(FranchiseePostDto franchiseePostDto) {
        if ( franchiseePostDto == null ) {
            return null;
        }

        Franchisee.FranchiseeBuilder franchisee = Franchisee.builder();

        franchisee.abn( franchiseePostDto.getAbn() );
        franchisee.postcode( franchiseePostDto.getPostcode() );
        franchisee.businessAddress( franchiseePostDto.getBusinessAddress() );
        franchisee.businessName( franchiseePostDto.getBusinessName() );
        franchisee.contactNumber( franchiseePostDto.getContactNumber() );
        franchisee.legalEntityName( franchiseePostDto.getLegalEntityName() );
        franchisee.state( franchiseePostDto.getState() );

        return franchisee.build();
    }

    @Override
    public FranchiseeGetDto franchiseeToGetDto(Franchisee franchisee) {
        if ( franchisee == null ) {
            return null;
        }

        FranchiseeGetDto.FranchiseeGetDtoBuilder franchiseeGetDto = FranchiseeGetDto.builder();

        franchiseeGetDto.franchiseeId( franchisee.getId() );
        franchiseeGetDto.abn( franchisee.getAbn() );
        franchiseeGetDto.businessAddress( franchisee.getBusinessAddress() );
        franchiseeGetDto.businessName( franchisee.getBusinessName() );
        franchiseeGetDto.legalEntityName( franchisee.getLegalEntityName() );
        franchiseeGetDto.isVerified( franchisee.getIsVerified() );
        franchiseeGetDto.postcode( franchisee.getPostcode() );
        franchiseeGetDto.state( franchisee.getState() );

        return franchiseeGetDto.build();
    }
}
