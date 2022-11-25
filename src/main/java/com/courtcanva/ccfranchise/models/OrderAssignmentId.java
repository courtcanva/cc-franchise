package com.courtcanva.ccfranchise.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderAssignmentId implements Serializable {

    @Column(name = "franchisee_id")
    private Long franchiseeId;

    @Column(name = "order_id")
    private Long orderId;

}
