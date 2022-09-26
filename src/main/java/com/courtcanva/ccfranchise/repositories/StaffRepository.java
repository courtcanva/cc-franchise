package com.courtcanva.ccfranchise.repositories;

import com.courtcanva.ccfranchise.models.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StaffRepository extends JpaRepository<Staff, Long> {
    Boolean existsStaffByEmail(String email);

}
