package com.courtcanva.ccfranchise.repositories;

import com.courtcanva.ccfranchise.models.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StaffRepository extends JpaRepository<Staff, Long> {
    
    Boolean existsStaffByEmail(String email);

    Optional<Staff> findByEmail(String email);

    Optional<Staff> findOneByVerificationTokenAndEmail(String verificationToken, String email);

}
