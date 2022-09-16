package com.courtcanva.ccfranchise.model;

import com.courtcanva.ccfranchise.constants.VerifyStatus;
import lombok.*;
import org.checkerframework.checker.units.qual.C;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "franchisees")
public class Franchise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Type(type = "long")
    private Long id;

    @Column(unique = true, nullable = false)
    private Integer ABN;

    @Column(nullable = false)
    private Integer postcode;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private VerifyStatus status;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String name;

    @Column
    private String dutyArea;

    @Column(nullable = false)
    private OffsetDateTime createdTime;

    @Column(nullable = false)
    private OffsetDateTime updatedTime;

    private OffsetDateTime approvedTime;

    @Column(name = "approved_by")
    private Long employeeId;

    @OneToMany(mappedBy = "franchise",
            cascade = CascadeType.ALL)
    private Set<Staff> staffs = new HashSet<>();
}
