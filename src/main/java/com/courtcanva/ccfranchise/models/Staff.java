package com.courtcanva.ccfranchise.models;

import com.courtcanva.ccfranchise.constants.AUState;
import com.courtcanva.ccfranchise.constants.BusinessRole;
import com.courtcanva.ccfranchise.constants.StaffStatus;
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
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.OffsetDateTime;


@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "staff")
public class Staff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private AUState state;

    @Column(nullable = false)
    private int postcode;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(name = "address")
    private String residentialAddress;

    @Column
    @Builder.Default
    private Boolean isVerified = false;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private StaffStatus status = StaffStatus.PENDING;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private BusinessRole businessRole = BusinessRole.STAFF;

    @Column
    private String verificationToken;

    @Column(name = "verification_token_created_at")
    private OffsetDateTime verificationTokenCreatedTime;

    @Column(nullable = false, updatable = false, name = "created_at")
    @CreationTimestamp
    private OffsetDateTime createdTime;

    @Column(nullable = false, name = "updated_at")
    @UpdateTimestamp
    private OffsetDateTime updatedTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "franchisee_id")
    private Franchisee franchisee;

}
