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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderAssignmentId that = (OrderAssignmentId) o;
        return Objects.equals(franchiseeId, that.franchiseeId) && Objects.equals(orderId, that.orderId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(franchiseeId, orderId);
    }
}
