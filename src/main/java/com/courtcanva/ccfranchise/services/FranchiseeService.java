package com.courtcanva.ccfranchise.services;

import com.courtcanva.ccfranchise.dtos.FranchiseeAndStaffDto;
import com.courtcanva.ccfranchise.dtos.FranchiseePostDto;
import com.courtcanva.ccfranchise.dtos.StaffGetDto;
import com.courtcanva.ccfranchise.dtos.StaffPostDto;
import com.courtcanva.ccfranchise.dtos.orders.OrderGetDto;
import com.courtcanva.ccfranchise.dtos.orders.OrderListGetDto;
import com.courtcanva.ccfranchise.dtos.orders.OrderListPostDto;
import com.courtcanva.ccfranchise.dtos.orders.OrderPostDto;
import com.courtcanva.ccfranchise.dtos.suburbs.SuburbListGetDto;
import com.courtcanva.ccfranchise.dtos.suburbs.SuburbListPostDto;
import com.courtcanva.ccfranchise.dtos.suburbs.SuburbPostDto;
import com.courtcanva.ccfranchise.exceptions.ResourceAlreadyExistException;
import com.courtcanva.ccfranchise.exceptions.ResourceNotFoundException;
import com.courtcanva.ccfranchise.exceptions.SelectNullOrder;
import com.courtcanva.ccfranchise.mappers.FranchiseeMapper;
import com.courtcanva.ccfranchise.mappers.StaffMapper;
import com.courtcanva.ccfranchise.mappers.SuburbMapper;
import com.courtcanva.ccfranchise.models.Franchisee;
import com.courtcanva.ccfranchise.models.Order;
import com.courtcanva.ccfranchise.models.Staff;
import com.courtcanva.ccfranchise.models.Suburb;
import com.courtcanva.ccfranchise.repositories.FranchiseeRepository;
import com.courtcanva.ccfranchise.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class FranchiseeService {
    private final FranchiseeRepository franchiseeRepository;

    private final FranchiseeMapper franchiseeMapper;

    private final StaffMapper staffMapper;

    private final StaffService staffService;

    private final PasswordEncoder passwordEncoder;

    private final SuburbService suburbService;

    private final SuburbMapper suburbMapper;
    private final OrderRepository orderRepository;

    @Transactional
    public FranchiseeAndStaffDto createFranchiseeAndStaff(FranchiseePostDto franchiseePostDto, StaffPostDto staffPostDto) {

        if (franchiseeExists(franchiseePostDto.getAbn())) {

            log.debug("franchisee with abn: {} already exist", franchiseePostDto.getAbn());

            throw new ResourceAlreadyExistException("franchisee already exist");

        }

        Franchisee franchisee = franchiseeRepository
                .save(franchiseeMapper.postDtoToFranchisee(franchiseePostDto));


        Staff staff = staffMapper.postDtoToStaff(staffPostDto);
        staff.setPassword(passwordEncoder.encode(staffPostDto.getPassword()));
        staff.setFranchisee(franchisee);

        StaffGetDto staffGetDto = staffService.createStaff(staff);

        return FranchiseeAndStaffDto.builder()
                .staffGetDto(staffGetDto)
                .franchiseeGetDto(franchiseeMapper.franchiseeToGetDto(franchisee))
                .build();
    }

    @Transactional
    public SuburbListGetDto addDutyAreas(SuburbListPostDto suburbListPostDto, Long franchiseeId) {

        Optional<Franchisee> optionalFranchisee = findFranchiseeById(franchiseeId);

        Franchisee franchisee = optionalFranchisee.orElseThrow(() -> {

            log.debug("franchisee with id: {} is not exist", franchiseeId);

            return new ResourceNotFoundException("franchisee id is not exist");

        });

        List<Suburb> allSuburbs = suburbService.findSuburbBySscCodes(suburbListPostDto.getSuburbs()
                .stream()
                .map(SuburbPostDto::getSscCode)
                .collect(Collectors.toList()));

        franchisee.addDutyAreas(allSuburbs);
        franchiseeRepository.save(franchisee);

        return SuburbListGetDto.builder().suburbs(allSuburbs
                        .stream()
                        .map(suburbMapper::suburbToGetDto)
                        .collect(Collectors.toList()))
                .build();
    }


    public boolean franchiseeExists(String abn) {

        return franchiseeRepository.existsFranchiseeByAbn(abn);

    }

    public Optional<Franchisee> findFranchiseeById(Long franchiseeId) {

        return franchiseeRepository.findFranchiseeById(franchiseeId);

    }

    @Transactional
    public OrderListGetDto acceptOrders(OrderListPostDto orderListPostDto) {

        List<Order> orders = selectedOrders(orderListPostDto)
                .stream()
                .peek(order -> order.setStatus("ASSIGNED"))
                .collect(Collectors.toList());

        return OrderListGetDto.builder()
                .orders(orderRepository.saveAll(orders)
                .stream()
                .map(order -> new OrderGetDto(order.getId(),order.getOrderId(),order.getStatus(),order.getContactInformation()))
                .collect(Collectors.toList())).build();
    }

    public List<Order> selectedOrders(OrderListPostDto orderListPostDto){

        List<Order> Orders = orderRepository.findAllById(
                orderListPostDto.getOrders()
                        .stream().map(OrderPostDto::getId)
                        .collect(Collectors.toList()));

        if (Orders.size() == 0) {

            throw new SelectNullOrder("You have not select any order.");
        }

        return Orders;
    }
}
