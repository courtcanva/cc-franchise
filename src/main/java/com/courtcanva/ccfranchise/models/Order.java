package com.courtcanva.ccfranchise.models;


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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "`order`")
// @TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String orderId;

    @Column(nullable = false)
    private String customerId;

    // @Type(type = "jsonb")
    // @Column(nullable = false, columnDefinition = "jsonb")
    @Column(nullable = false)
    private String contactInformation;

    // @Type(type = "jsonb")
    // @Column(nullable = false, columnDefinition = "jsonb")
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
    private String status;

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



//        courts: [{
//        quotation: string,
//        constructionDraw: string(url),
//        isNeedLevelGround: boolean,
//        design: {
//        designName: string,
//        tileColor: [{
//        location: string,
//        color: string
//        }],
//        courtSize: {
//        name: string,
//        length: number,
//        width: number,
//        centreCircleRadius: number,
//        threePointRadius: number,
//        threePointLine: number,
//        lengthOfCorner: number,
//        restrictedAreaLength: number,
//        restrictedAreaWidth: number,
//        sideBorderWidth: number,
//        lineBorderWidth: number,
//        }
//        }
//        quotationDetails: [{
//        color: string,
//        quantity: number
//        }],
//        constructionAddress: {
//        country: string,
//        state: string,
//        city: string,
//        line1: string,
//        ?line2: string,
//        postal_code: string,
//        }
//        }],
//
//