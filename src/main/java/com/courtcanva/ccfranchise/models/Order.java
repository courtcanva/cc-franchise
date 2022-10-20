package com.courtcanva.ccfranchise.models;


import com.courtcanva.ccfranchise.constants.EmployeeStatus;
import com.courtcanva.ccfranchise.constants.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.UpdateTimestamp;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import javax.persistence.*;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "`order`")
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String orderId;

    @Column(nullable = false)
    private String customerId;

    @Type(type = "jsonb")
    @Column(nullable = false, columnDefinition = "jsonb")
    private String contactInformation;

    @Type(type = "jsonb")
    @Column(nullable = false, columnDefinition = "jsonb")
    private String designInformation;

    @Column(nullable = false)
    private String postcode;

    @Column(nullable = false)
    private BigDecimal totalAmount;

    @Column(nullable = false)
    private BigDecimal paidAmount;

    @Column(nullable = false)
    private BigDecimal unpaidAmount;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private OrderStatus status= OrderStatus.UNASSIGNED;

    @Column(nullable = false, updatable = false, name = "created_at")
    @CreationTimestamp
    private OffsetDateTime createdTime;

    @Column(nullable = false, name = "updated_at")
    @UpdateTimestamp
    private OffsetDateTime updatedTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "franchisee_id")
    private Franchisee franchisee;

    @Column
    private String invoiceLink;
}