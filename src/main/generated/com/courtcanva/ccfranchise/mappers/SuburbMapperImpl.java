package com.courtcanva.ccfranchise.mappers;

import com.courtcanva.ccfranchise.dtos.suburbs.SuburbGetDto;
import com.courtcanva.ccfranchise.dtos.suburbs.SuburbPostDto;
import com.courtcanva.ccfranchise.models.Suburb;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-10-30T21:01:54+1000",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 17.0.4.1 (Amazon.com Inc.)"
)
@Component
public class SuburbMapperImpl implements SuburbMapper {

    @Override
    public Suburb postDtoToSuburb(SuburbPostDto suburbPostDto) {
        if ( suburbPostDto == null ) {
            return null;
        }

        Suburb.SuburbBuilder suburb = Suburb.builder();

        suburb.sscCode( suburbPostDto.getSscCode() );

        return suburb.build();
    }

    @Override
    public SuburbGetDto suburbToGetDto(Suburb suburb) {
        if ( suburb == null ) {
            return null;
        }

        SuburbGetDto.SuburbGetDtoBuilder suburbGetDto = SuburbGetDto.builder();

        suburbGetDto.sscCode( suburb.getSscCode() );
        suburbGetDto.suburbName( suburb.getSuburbName() );
        suburbGetDto.postcode( suburb.getPostcode() );
        suburbGetDto.state( suburb.getState() );

        return suburbGetDto.build();
    }
}
