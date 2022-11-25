package com.courtcanva.ccfranchise.mappers;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.courtcanva.ccfranchise.dtos.orders.OrderOpenGetDto;
import com.courtcanva.ccfranchise.models.DesignInformation;
import com.courtcanva.ccfranchise.models.Order;
import com.courtcanva.ccfranchise.utils.FranchiseeTestHelper;
import com.courtcanva.ccfranchise.utils.OrderTestHelper;
import org.junit.jupiter.api.Test;

class OrderMapperTest {

    @Test
    void givenEmptyDesignInfo_whenMapping_shouldReturnDesignInfoInstance() {
        Order orderWithEmptyDesignInformation = OrderTestHelper.createOrder("101", "3000", 3000L, FranchiseeTestHelper.createFranchiseeWithId());
        orderWithEmptyDesignInformation.setDesignInformation("");
        OrderOpenGetDto orderOpenGetDto = ((OrderMapper) new OrderMapperImpl()).orderOpenGetDto(orderWithEmptyDesignInformation);
        assertNotNull(orderOpenGetDto.getDesignInformation());
        assertInstanceOf(DesignInformation.class, orderOpenGetDto.getDesignInformation());
    }

}