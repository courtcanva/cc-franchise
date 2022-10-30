package com.courtcanva.ccfranchise.mappers;

import com.courtcanva.ccfranchise.dtos.StaffGetDto;
import com.courtcanva.ccfranchise.dtos.StaffPostDto;
import com.courtcanva.ccfranchise.models.Staff;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-10-30T21:01:53+1000",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 17.0.4.1 (Amazon.com Inc.)"
)
@Component
public class StaffMapperImpl implements StaffMapper {

    @Override
    public Staff postDtoToStaff(StaffPostDto staffPostDto) {
        if ( staffPostDto == null ) {
            return null;
        }

        Staff.StaffBuilder staff = Staff.builder();

        staff.firstName( staffPostDto.getFirstName() );
        staff.lastName( staffPostDto.getLastName() );
        staff.password( staffPostDto.getPassword() );
        staff.state( staffPostDto.getState() );
        staff.postcode( staffPostDto.getPostcode() );
        staff.phoneNumber( staffPostDto.getPhoneNumber() );
        staff.residentialAddress( staffPostDto.getResidentialAddress() );
        staff.email( staffPostDto.getEmail() );

        return staff.build();
    }

    @Override
    public StaffGetDto staffToGetDto(Staff staff) {
        if ( staff == null ) {
            return null;
        }

        StaffGetDto.StaffGetDtoBuilder staffGetDto = StaffGetDto.builder();

        staffGetDto.staffId( staff.getId() );
        staffGetDto.email( staff.getEmail() );
        staffGetDto.state( staff.getState() );
        staffGetDto.firstName( staff.getFirstName() );
        staffGetDto.lastName( staff.getLastName() );
        staffGetDto.phoneNumber( staff.getPhoneNumber() );
        staffGetDto.residentialAddress( staff.getResidentialAddress() );
        staffGetDto.isVerified( staff.getIsVerified() );

        return staffGetDto.build();
    }
}
