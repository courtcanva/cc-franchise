package com.courtcanva.ccfranchise.utils;

import com.courtcanva.ccfranchise.constants.OrderStatus;
import com.courtcanva.ccfranchise.dtos.orders.OrderAcceptedAndCompletedPaginationGetDto;
import com.courtcanva.ccfranchise.dtos.orders.OrderGetDto;
import com.courtcanva.ccfranchise.dtos.orders.OrderListPostDto;
import com.courtcanva.ccfranchise.dtos.orders.OrderPostDto;
import com.courtcanva.ccfranchise.models.Franchisee;
import com.courtcanva.ccfranchise.models.Order;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrderTestHelper {
    public static List<Order> orders = List.of(
            OrderTestHelper.createOrder("101", "3000", 3000L, FranchiseeTestHelper.createFranchiseeWithId()),
            OrderTestHelper.createOrder("102", "4000", 4000L, FranchiseeTestHelper.createFranchiseeWithId()));

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

    public static Order mockAcceptedOrder1() {
        return Order.builder()
                .id(3L)
                .orderId("111")
                .customerId("102")
                .unpaidAmount(BigDecimal.valueOf(998.00))
                .postcode("3003")
                .totalAmount(BigDecimal.valueOf(999.00))
                .designInformation("{\"name\": \"draft 1\"}")
                .paidAmount(BigDecimal.valueOf(1.00))
                .contactInformation("{\"name\": \"Alex\", \"phone\": \"0404123457\"}")
                .status(OrderStatus.ACCEPTED)
                .build();
    }

    public static OrderAcceptedAndCompletedPaginationGetDto mockAcceptedListDto() {
        return OrderAcceptedAndCompletedPaginationGetDto.builder()
                .acceptedOrders(List.of(
                        OrderGetDto.builder()
                                .orderId("802")
                                .build()
                ))
                .PageNumber(1)
                .build();
    }

    public static Order createOrder(String customerId, String postcode, Long totalAmount, Franchisee franchisee) {
        return Order.builder()
                .orderId("101")
                .customerId(customerId)
                .contactInformation("""
                        {"name": "Adam", "phone": "0404123456", "address": "Unit 1, 10 Queen Street, Richmond 3121"}""")
                .designInformation("""
                        {"name": "draft version 1"}""")
                .postcode(postcode)
                .totalAmount(new BigDecimal(totalAmount))
                .paidAmount(new BigDecimal(1000L))
                .unpaidAmount(new BigDecimal(2000L))
                .status(OrderStatus.ASSIGNED_PENDING)
                .franchisee(franchisee)
                .invoiceLink("https://link.co")
                .createdTime(OffsetDateTime.parse("2021-12-03T10:15:30+11:00"))
                .updatedTime(OffsetDateTime.parse("2021-12-03T10:15:30+11:00"))
                .build();
    }
}

