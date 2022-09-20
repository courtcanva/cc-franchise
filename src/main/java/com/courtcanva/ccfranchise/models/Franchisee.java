package com.courtcanva.ccfranchise.models;

import com.courtcanva.ccfranchise.constants.AUState;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "franchisee")
public class Franchisee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String abn;

    @Column(nullable = false)
    private int postcode;

    @Column
    private Boolean isVerified;

    @Column(nullable = false)
    private String businessAddress;

    @Column(nullable = false)
    private String businessName;

    @Column
    private String dutyArea;

    @Column(nullable = false)
    private String entityName;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private AUState state;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private OffsetDateTime createdTime;

    @Column(nullable = false)
    @UpdateTimestamp
    private OffsetDateTime updatedTime;

    @Column
    private OffsetDateTime approvedTime;

    @JoinColumn(name = "approvedBy")
    @ManyToOne(fetch = FetchType.LAZY)
    private CcEmployee ccEmployee;

    @OneToMany(mappedBy = "franchisee")
    private Set<Staff> staffs = new HashSet<>();
}
