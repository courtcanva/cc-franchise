package com.courtcanva.ccfranchise.model;

import com.courtcanva.ccfranchise.constants.StaffRole;
import com.courtcanva.ccfranchise.constants.VerifyStatus;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.Objects;

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "franchise")
public class Staff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    private String password;

    private String state;
    private Integer postcode;

    private String phoneNumber;

    private String verificationDocumentLink;

    private String address;

    @Enumerated(EnumType.STRING)
    private VerifyStatus status;

    @Column(unique = true, nullable = false)
    private String email;


    @Enumerated(EnumType.STRING)
    private StaffRole role;

    @Column( nullable = false)
    @CreationTimestamp
    private OffsetDateTime createdTime;

    @Column(nullable = false)
    @UpdateTimestamp
    private OffsetDateTime updatedTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "franchise_id")
    private Franchisee franchisee;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Staff staff = (Staff) o;
        return id != null && Objects.equals(id, staff.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
