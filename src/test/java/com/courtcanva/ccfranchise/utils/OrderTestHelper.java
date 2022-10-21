package com.courtcanva.ccfranchise.utils;

import com.courtcanva.ccfranchise.constants.OrderStatus;
import com.courtcanva.ccfranchise.dtos.orders.OrderListPostDto;
import com.courtcanva.ccfranchise.dtos.orders.OrderPostDto;
import com.courtcanva.ccfranchise.models.Order;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class OrderTestHelper {
    public static OrderListPostDto createOrderListPostDto() {
        List<OrderPostDto> orders = new ArrayList<>();
        orders.add(new OrderPostDto(1L));
        orders.add(new OrderPostDto(2L));
        return OrderListPostDto.builder().orders(orders).build();
    }

    public static OrderListPostDto createEmptyOrderListPostDto() {
        List<OrderPostDto> orders = new ArrayList<>();
        orders.add(new OrderPostDto());
        return OrderListPostDto.builder().orders(orders).build();
    }

    public static Order Order1() {
        return Order.builder()
                .id(1L)
                .orderId("102")
                .customerId("102")
                .designInformation("{\"name\": \"draft 1\"}")
                .contactInformation("{\"name\": \"Alex\", \"phone\": \"0404123457\"}")
                .postcode("3003")
                .unpaidAmount(BigDecimal.valueOf(998.00))
                .totalAmount(BigDecimal.valueOf(999.00))
                .paidAmount(BigDecimal.valueOf(1.00))
                .status(OrderStatus.UNASSIGNED)
                .build();
    }

    public static Order Order2() {
        return Order.builder()
                .id(2L)
                .orderId("102")
                .customerId("102")
                .designInformation("{\"name\": \"draft 1\"}")
                .contactInformation("{\"name\": \"Alex\", \"phone\": \"0404123457\"}")
                .postcode("3003")
                .unpaidAmount(BigDecimal.valueOf(998.00))
                .totalAmount(BigDecimal.valueOf(999.00))
                .paidAmount(BigDecimal.valueOf(1.00))
                .status(OrderStatus.UNASSIGNED)
                .build();
    }

    public static List<Order> OrderList() {
        List<Order> orders = new ArrayList<>();
        Order order1 = Order.builder()
                .id(1L)
                .orderId("102")
                .customerId("102")
                .designInformation("{\"name\": \"draft 1\"}")
                .contactInformation("{\"name\": \"Alex\", \"phone\": \"0404123457\"}")
                .postcode("3003")
                .unpaidAmount(BigDecimal.valueOf(998.00))
                .totalAmount(BigDecimal.valueOf(999.00))
                .paidAmount(BigDecimal.valueOf(1.00))
                .status(OrderStatus.ASSIGNED_PENDING)
                .franchisee(FranchiseeTestHelper.createFranchiseeWithId())
                .build();
        Order order2 = Order.builder()
                .id(2L)
                .orderId("102")
                .customerId("102")
                .designInformation("{\"name\": \"draft 1\"}")
                .contactInformation("{\"name\": \"Alex\", \"phone\": \"0404123457\"}")
                .postcode("3003")
                .unpaidAmount(BigDecimal.valueOf(998.00))
                .totalAmount(BigDecimal.valueOf(999.00))
                .paidAmount(BigDecimal.valueOf(1.00))
                .status(OrderStatus.ASSIGNED_PENDING)
                .franchisee(FranchiseeTestHelper.createFranchiseeWithId())
                .build();
        orders.add(0, (order1));
        orders.add(1, (order2));
        return orders;
    }

    public static List<Order> AcceptedOrderList() {
        List<Order> orders = new ArrayList<>();
        Order order1 = Order.builder()
                .id(1L)
                .orderId("102")
                .customerId("102")
                .designInformation("{\"name\": \"draft 1\"}")
                .contactInformation("{\"name\": \"Alex\", \"phone\": \"0404123457\"}")
                .postcode("3003")
                .unpaidAmount(BigDecimal.valueOf(998.00))
                .totalAmount(BigDecimal.valueOf(999.00))
                .paidAmount(BigDecimal.valueOf(1.00))
                .status(OrderStatus.ACCEPTED)
                .franchisee(FranchiseeTestHelper.createFranchiseeWithId())
                .build();
        Order order2 = Order.builder()
                .id(2L)
                .orderId("102")
                .customerId("102")
                .designInformation("{\"name\": \"draft 1\"}")
                .contactInformation("{\"name\": \"Alex\", \"phone\": \"0404123457\"}")
                .postcode("3003")
                .unpaidAmount(BigDecimal.valueOf(998.00))
                .totalAmount(BigDecimal.valueOf(999.00))
                .paidAmount(BigDecimal.valueOf(1.00))
                .status(OrderStatus.ACCEPTED)
                .franchisee(FranchiseeTestHelper.createFranchiseeWithId())
                .build();
        orders.add(0, (order1));
        orders.add(1, (order2));
        return orders;
    }


}
