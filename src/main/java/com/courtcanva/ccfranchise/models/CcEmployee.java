package com.courtcanva.ccfranchise.models;

import com.courtcanva.ccfranchise.constants.BusinessRole;
import com.courtcanva.ccfranchise.constants.EmployeeStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "cc_employee")
public class CcEmployee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private BusinessRole businessRole = BusinessRole.STAFF;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String password;

    @Column
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private EmployeeStatus status = EmployeeStatus.INACTIVE;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private OffsetDateTime createdTime;

    @Column(nullable = false)
    @UpdateTimestamp
    private OffsetDateTime updatedTime;

    @OneToMany(mappedBy = "ccEmployee")
    private Set<Franchisee> franchiseeVerificationSet = new HashSet<>();
}
