package com.courtcanva.ccfranchise.services;

import com.courtcanva.ccfranchise.constants.DutyAreaFilterMode;
import com.courtcanva.ccfranchise.constants.OrderAssignmentStatus;
import com.courtcanva.ccfranchise.constants.OrderStatus;
import com.courtcanva.ccfranchise.dtos.FranchiseeAndStaffDto;
import com.courtcanva.ccfranchise.dtos.FranchiseePostDto;
import com.courtcanva.ccfranchise.dtos.StaffGetDto;
import com.courtcanva.ccfranchise.dtos.StaffPostDto;
import com.courtcanva.ccfranchise.dtos.orders.*;
import com.courtcanva.ccfranchise.dtos.suburbs.SuburbListAndFilterModeGetDto;
import com.courtcanva.ccfranchise.dtos.suburbs.SuburbListAndFilterModePostDto;
import com.courtcanva.ccfranchise.dtos.suburbs.SuburbPostDto;
import com.courtcanva.ccfranchise.exceptions.MailingClientException;
import com.courtcanva.ccfranchise.exceptions.ResourceAlreadyExistException;
import com.courtcanva.ccfranchise.exceptions.ResourceNotFoundException;
import com.courtcanva.ccfranchise.mappers.FranchiseeMapper;
import com.courtcanva.ccfranchise.mappers.OrderMapper;
import com.courtcanva.ccfranchise.mappers.StaffMapper;
import com.courtcanva.ccfranchise.mappers.SuburbMapper;
import com.courtcanva.ccfranchise.models.*;
import com.courtcanva.ccfranchise.models.Franchisee;
import com.courtcanva.ccfranchise.models.Order;
import com.courtcanva.ccfranchise.models.Staff;
import com.courtcanva.ccfranchise.models.Suburb;
import com.courtcanva.ccfranchise.models.OrderAssignment;
import com.courtcanva.ccfranchise.repositories.FranchiseeRepository;
import com.courtcanva.ccfranchise.repositories.OrderAssignmentRepository;
import com.courtcanva.ccfranchise.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
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

    private final OrderMapper orderMapper;

    private final OrderRepository orderRepository;

    private final OrderAssignmentRepository orderAssignmentRepository;


    @Transactional(noRollbackFor = MailingClientException.class)
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

    public SuburbListAndFilterModeGetDto dutyAreas(SuburbListAndFilterModePostDto suburbListAndFilterModePostDto, Long franchiseeId) {

        return suburbListAndFilterModePostDto.getFilterMode().equals(DutyAreaFilterMode.INCLUDE) ? addDutyAreas(suburbListAndFilterModePostDto, franchiseeId) : null;

    }

    @Transactional
    public SuburbListAndFilterModeGetDto addDutyAreas(SuburbListAndFilterModePostDto suburbListAndFilterModePostDto, Long franchiseeId) {

        Franchisee franchisee = findFranchiseeById(franchiseeId).orElseThrow(() -> {
            log.debug("franchisee with id: {} is not exist", franchiseeId);
            return new ResourceNotFoundException("franchisee id is not exist");

        });

        List<Suburb> allSuburbs = suburbService.findSuburbBySscCodes(suburbListAndFilterModePostDto.getSuburbs()
                .stream()
                .map(SuburbPostDto::getSscCode)
                .collect(Collectors.toList()));

        franchisee.addDutyAreas(allSuburbs);
        franchiseeRepository.save(franchisee);

        return SuburbListAndFilterModeGetDto.builder()
                .filterMode(suburbListAndFilterModePostDto.getFilterMode())
                .suburbs(allSuburbs
                        .stream()
                        .map(suburbMapper::suburbToGetDto)
                        .collect(Collectors.toList()))
                .build();
    }

    public boolean franchiseeExists(String abn) {

        return franchiseeRepository.existsFranchiseeByAbn(abn);

    }

    public boolean abnExists(String abn) {
        boolean isExisted = franchiseeRepository.existsFranchiseeByAbn(abn);
        if (isExisted) {
            throw new ResourceAlreadyExistException("ABN already existed");
        }
        return false;
    }

    public Optional<Franchisee> findFranchiseeById(Long franchiseeId) {
        return franchiseeRepository.findFranchiseeById(franchiseeId);
    }


    @Transactional
    public OrderListGetDto acceptOrders(OrderListPostDto orderListPostDto) {
        List<Order> selectedOrders = orderRepository.findByIdIn(
                orderListPostDto.getOrders()
                        .stream()
                        .map(OrderPostDto::getId)
                        .collect(Collectors.toList()));

        if (selectedOrders.isEmpty()) {

            log.debug("selected order id: {} is empty", orderListPostDto.getOrders());
            throw new ResourceNotFoundException("You have not selected any order.");
        }

        OffsetDateTime currentTime = OffsetDateTime.now();
        selectedOrders.forEach(order -> {
            order.setStatus(OrderStatus.ACCEPTED);
            order.setUpdatedTime(currentTime);
        });

        List<OrderAssignment> assignedOrders = orderAssignmentRepository.findByOrderIdIn(
                orderListPostDto.getOrders()
                        .stream()
                        .map(OrderPostDto::getId)
                        .collect(Collectors.toList()));
        assignedOrders.forEach(orderAssignment -> {
            orderAssignment.setStatus(OrderAssignmentStatus.ACCEPTED);
            orderAssignment.setUpdatedTime(currentTime);
        });

        List<Order> acceptedOrderList = orderRepository.saveAll(selectedOrders);
        return OrderListGetDto.builder()
                .orders(acceptedOrderList
                        .stream()
                        .map(orderMapper::orderToGetDto)
                        .collect(Collectors.toList())).build();
    }

    @Transactional
    public boolean rejectOrders(OrderAssignmentListPostDto orderAssignmentListPostDto) {
        List<OrderAssignmentPostDto> orderAssignmentPostDtos = orderAssignmentListPostDto.getOrderAssignments();
        List<OrderAssignment> orderAssignments = orderAssignmentRepository.findOrderAssignmentByIdIn(orderAssignmentPostDtos
                .stream()
                .map(OrderAssignmentPostDto::getId).
                toList());
        if (orderAssignments.isEmpty()) {
            return true;
        }
        List<Order> orders = orderRepository.findByIdIn(orderAssignmentPostDtos
                .stream()
                .map(OrderAssignmentPostDto::getId)
                .toList()
                .stream().map(OrderAssignmentId::getOrderId).toList());
        for (Order order : orders) {
            order.setStatus(OrderStatus.UNASSIGNED);
            order.setFranchisee(null);
        }
        updateStatusAndUpdateAt(orderAssignments);
        orderRepository.saveAll(orders);
        orderAssignmentRepository.saveAll(orderAssignments);
        return true;
    }

    private static void updateStatusAndUpdateAt(List<OrderAssignment> orderAssignments) {
        OffsetDateTime now = OffsetDateTime.now();
        for (OrderAssignment orderAssignment : orderAssignments) {
            orderAssignment.setStatus(OrderAssignmentStatus.REJECTED);
            orderAssignment.setUpdatedTime(now);
        }
    }

    public List<Franchisee> findMatchedFranchisee(long sscCode, long orderId) {

        Set<Suburb> dutyAreas = new HashSet<>(suburbService.findSuburbBySscCodes(List.of(sscCode)));

        return franchiseeRepository.findFranchiseesByDutyAreasIn(dutyAreas).stream()
                .filter(franchisee -> franchisee.getOrderAssignmentSet().stream().noneMatch(orderAssignment ->
                        Objects.equals(orderAssignment.getOrder().getId(), orderId)
                                && orderAssignment.getStatus().equals(OrderAssignmentStatus.REJECTED)))
                .filter(franchisee -> franchisee.getOrderAssignmentSet().stream()
                        .filter(orderAssignment -> !orderAssignment.getStatus().equals(OrderAssignmentStatus.REJECTED)
                                && !orderAssignment.getStatus().equals(OrderAssignmentStatus.COMPLETED)).toList().size() < 10)
                .sorted(Comparator.comparing(Franchisee::getBusinessName))
                .toList();

    }
}