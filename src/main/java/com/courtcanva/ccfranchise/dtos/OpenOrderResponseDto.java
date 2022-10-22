package com.courtcanva.ccfranchise.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Objects;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OpenOrderResponseDto {

    private OffsetDateTime createdTime;

    private String customerId;

    private String contactInformation;

    private String postcode;

    private BigDecimal totalAmount;

    @Override public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        OpenOrderResponseDto that = (OpenOrderResponseDto) o;
        return customerId.equals(that.customerId) && contactInformation.equals(that.contactInformation) && postcode.equals(that.postcode)
                   && totalAmount.equals(that.totalAmount);
    }

    @Override public int hashCode() {
        return Objects.hash(customerId, contactInformation, postcode, totalAmount);
    }
}
