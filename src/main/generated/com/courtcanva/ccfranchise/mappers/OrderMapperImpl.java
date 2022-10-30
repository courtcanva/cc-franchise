package com.courtcanva.ccfranchise.mappers;

import com.courtcanva.ccfranchise.dtos.orders.OrderAcceptedGetDto;
import com.courtcanva.ccfranchise.dtos.orders.OrderGetDto;
import com.courtcanva.ccfranchise.dtos.orders.OrderPostDto;
import com.courtcanva.ccfranchise.models.Order;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-10-30T21:01:54+1000",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 17.0.4.1 (Amazon.com Inc.)"
)
@Component
public class OrderMapperImpl implements OrderMapper {

    @Override
    public Order postDtoToOrder(OrderPostDto orderPostDto) {
        if ( orderPostDto == null ) {
            return null;
        }

        Order.OrderBuilder order = Order.builder();

        order.id( orderPostDto.getId() );

        return order.build();
    }

    @Override
    public OrderGetDto orderToGetDto(Order order) {
        if ( order == null ) {
            return null;
        }

        OrderGetDto.OrderGetDtoBuilder orderGetDto = OrderGetDto.builder();

        orderGetDto.id( order.getId() );
        orderGetDto.orderId( order.getOrderId() );
        orderGetDto.status( order.getStatus() );
        orderGetDto.contactInformation( order.getContactInformation() );

        return orderGetDto.build();
    }

    @Override
    public OrderAcceptedGetDto orderToAcceptedGetDto(Order order) {
        if ( order == null ) {
            return null;
        }

        OrderAcceptedGetDto.OrderAcceptedGetDtoBuilder orderAcceptedGetDto = OrderAcceptedGetDto.builder();

        orderAcceptedGetDto.id( order.getId() );
        orderAcceptedGetDto.orderId( order.getOrderId() );
        orderAcceptedGetDto.status( order.getStatus() );
        orderAcceptedGetDto.contactInformation( order.getContactInformation() );
        orderAcceptedGetDto.designInformation( order.getDesignInformation() );

        return orderAcceptedGetDto.build();
    }
}
