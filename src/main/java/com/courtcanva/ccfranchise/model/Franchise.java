package com.courtcanva.ccfranchise.model;

import com.courtcanva.ccfranchise.constants.VerifyStatus;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Objects;
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
    private String businessAddress;

    @Column(nullable = false)
    private String businessName;

    @Column
    private String dutyArea;

    @Column(nullable = false)
    @CreationTimestamp
    private OffsetDateTime createdTime;

    @Column(nullable = false)
    @UpdateTimestamp
    private OffsetDateTime updatedTime;

    private OffsetDateTime approvedTime;

    @Column(name = "approved_by")
    private Long employeeId;

    @OneToMany(mappedBy = "franchise")
    private Set<Staff> staffs = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Franchise franchise = (Franchise) o;
        return id != null && Objects.equals(id, franchise.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
