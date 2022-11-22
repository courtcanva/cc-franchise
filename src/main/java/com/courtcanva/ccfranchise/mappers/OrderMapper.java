package com.courtcanva.ccfranchise.mappers;

import com.courtcanva.ccfranchise.dtos.orders.OrderGetDto;
import com.courtcanva.ccfranchise.dtos.orders.OrderOpenPostDto;
import com.courtcanva.ccfranchise.dtos.orders.OrderPostDto;
import com.courtcanva.ccfranchise.models.DesignInformation;
import com.courtcanva.ccfranchise.models.Order;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderMapper {

    Logger log = LoggerFactory.getLogger(OrderMapper.class);

    Order postDtoToOrder(OrderPostDto orderPostDto);

    OrderGetDto orderToGetDto(Order order);

    @Mapping(source = "designInformation", target = "designInformation", qualifiedByName = "mapDesignInformation")
    OrderOpenPostDto orderOpenPostDto(Order order);

    @Named("mapDesignInformation")
    static DesignInformation mapDesignInformation(String designInformation) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(designInformation, DesignInformation.class);
        } catch (JsonProcessingException e) {
            log.debug("Jackson read Order.designInformation failed, will return null");
            log.debug("exception message: {}",e.getMessage());
            log.debug("failed deignInformation value is {}", designInformation);
            return new DesignInformation();
        }
    }

}
