package com.courtcanva.ccfranchise.repository;

import com.courtcanva.ccfranchise.dto.UsernamePasswordDto;
import org.springframework.stereotype.Repository;

@Repository
public class FranchiseeRepository {
    public boolean checkExists(UsernamePasswordDto usernamePasswordDto) {
        String username = usernamePasswordDto.getUsername();
        String password = usernamePasswordDto.getPassword();
        if ("a@gmail.com".equals(username) && "123456".equals(password)) {
            return true;
        } else {
            return false;
        }
        // TODO: 9/20/22 query database
    }
}
