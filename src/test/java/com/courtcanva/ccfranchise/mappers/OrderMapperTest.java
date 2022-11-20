package com.courtcanva.ccfranchise.mappers;

import static org.junit.jupiter.api.Assertions.*;

import com.courtcanva.ccfranchise.dtos.orders.OrderPendingPostDto;
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
        OrderPendingPostDto orderPendingPostDto = ((OrderMapper) new OrderMapperImpl()).orderPendingPostDto(orderWithEmptyDesignInformation);
        assertNotNull(orderPendingPostDto.getDesignInformation());
        assertInstanceOf(DesignInformation.class, orderPendingPostDto.getDesignInformation());
    }

}