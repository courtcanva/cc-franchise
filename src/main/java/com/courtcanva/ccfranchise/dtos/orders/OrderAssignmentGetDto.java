package com.courtcanva.ccfranchise.dtos.orders;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import java.time.OffsetDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderAssignmentGetDto {
    private Long id;

    private Long order_id;

    private OffsetDateTime createdTime;

    private String status;
}
