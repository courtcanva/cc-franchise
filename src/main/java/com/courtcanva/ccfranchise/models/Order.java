package com.courtcanva.ccfranchise.models;

import com.courtcanva.ccfranchise.constants.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "`order`")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String orderId;

    @Column(nullable = false)
    private String customerId;

    @Column(nullable = false)
    private String contactInformation;

    @Column(nullable = false)
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
    private OrderStatus status = OrderStatus.UNASSIGNED;

    @Column(nullable = false, updatable = false, name = "created_at")
    @CreationTimestamp
    private OffsetDateTime createdTime;

    @Column(nullable = false, name = "updated_at")
    @UpdateTimestamp
    private OffsetDateTime updatedTime;

    @JoinColumn(name = "franchisee_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Franchisee franchisee;

    @Column
    private String invoiceLink;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "details_id", referencedColumnName = "id")
    private OrderDetails orderDetails;
}