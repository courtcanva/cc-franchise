package com.courtcanva.ccfranchise.services;

import com.courtcanva.ccfranchise.dtos.UserDto;
import com.courtcanva.ccfranchise.dtos.UserGetDto;
import com.courtcanva.ccfranchise.exceptions.ResourceAlreadyExistException;
import com.courtcanva.ccfranchise.models.Staff;
import com.courtcanva.ccfranchise.repositories.StaffRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final StaffRepository staffRepository;

    public UserGetDto checkStaff(UserDto userDto) {
        if (checkUserIsExist(userDto.getEmail())) {
            return checkUserPassword(userDto);
        }

        log.error("staff with email: {} does not exist", userDto.getEmail());
        throw new ResourceAlreadyExistException("staff email does not exist");
    }

    public boolean checkUserIsExist(String email) {
        return staffRepository.existsStaffByEmail(email);

    }

    public UserGetDto checkUserPassword(UserDto userDto) {
        Staff curUser = staffRepository.findByEmail(userDto.getEmail());
        String password = curUser.getPassword();
        String curPassword = userDto.getPassword();
        System.out.println(password);
        System.out.println(curPassword);
        System.out.println(password.equals(curPassword));
        UserGetDto userGetDto = new UserGetDto(password.equals(userDto.getPassword()));
        return userGetDto;
    }
}
