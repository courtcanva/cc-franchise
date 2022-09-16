package com.courtcanva.ccfranchise.model;

import com.courtcanva.ccfranchise.constants.StaffRole;
import com.courtcanva.ccfranchise.constants.VerifyStatus;
import lombok.*;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "franchise")
public class Staff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String password;

    @Column
    private String state;

    @Column
    private String phoneNumber;

    @Column
    private String verificationDocumentLink;

    @Column
    private String address;

    @Column
    @Enumerated(EnumType.STRING)
    private VerifyStatus status;

    @Column(unique = true, nullable = false)
    private String email;

    @Column
    @Enumerated(EnumType.STRING)
    private StaffRole role;

    @Column(name = "created_time", nullable = false)
    private OffsetDateTime createdTime;

    @Column(name = "updated_time", nullable = false)
    private OffsetDateTime updatedTime;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "franchise_id")
    private Franchise franchise;

}
