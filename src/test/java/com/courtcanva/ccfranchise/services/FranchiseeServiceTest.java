package com.courtcanva.ccfranchise.services;


import com.courtcanva.ccfranchise.constants.OrderStatus;
import com.courtcanva.ccfranchise.dtos.FranchiseeAndStaffDto;
import com.courtcanva.ccfranchise.dtos.FranchiseePostDto;
import com.courtcanva.ccfranchise.dtos.StaffGetDto;
import com.courtcanva.ccfranchise.dtos.StaffPostDto;
import com.courtcanva.ccfranchise.dtos.orders.OrderListGetDto;
import com.courtcanva.ccfranchise.dtos.orders.OrderListPostDto;
import com.courtcanva.ccfranchise.dtos.suburbs.SuburbListGetDto;
import com.courtcanva.ccfranchise.dtos.suburbs.SuburbListPostDto;
import com.courtcanva.ccfranchise.exceptions.ResourceAlreadyExistException;
import com.courtcanva.ccfranchise.exceptions.ResourceNotFoundException;
import com.courtcanva.ccfranchise.mappers.*;
import com.courtcanva.ccfranchise.models.Franchisee;
import com.courtcanva.ccfranchise.models.Order;
import com.courtcanva.ccfranchise.models.Suburb;
import com.courtcanva.ccfranchise.repositories.FranchiseeRepository;
import com.courtcanva.ccfranchise.repositories.OrderRepository;
import com.courtcanva.ccfranchise.repositories.SuburbRepository;
import com.courtcanva.ccfranchise.utils.FranchiseeTestHelper;
import com.courtcanva.ccfranchise.utils.OrderTestHelper;
import com.courtcanva.ccfranchise.utils.StaffTestHelper;
import com.courtcanva.ccfranchise.utils.SuburbTestHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.Optional.empty;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class FranchiseeServiceTest {

    @Mock
    private FranchiseeRepository franchiseeRepository;

    @Mock
    private StaffService staffService;

    private FranchiseeService franchiseeService;

    @Mock
    private SuburbService suburbService;

    @Mock
    private SuburbRepository suburbRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private OrderRepository orderRepository;
    @Mock
    private OrderMapper orderMapper;


    @BeforeEach
    void setUp() {
        franchiseeRepository.save(FranchiseeTestHelper.createFranchiseeWithId());
        suburbRepository.save(SuburbTestHelper.suburb1());
        suburbRepository.save(SuburbTestHelper.suburb2());
    }

    @BeforeEach
    void setOrderRepositoryUp() {
        orderRepository.save(OrderTestHelper.Order1());
        orderRepository.save(OrderTestHelper.Order2());
    }

    @BeforeEach
    public void setFranchiseeServiceUp() {

        FranchiseeMapper franchiseeMapper = new FranchiseeMapperImpl();
        StaffMapper staffMapper = new StaffMapperImpl();
        SuburbMapper suburbMapper = new SuburbMapperImpl();
        franchiseeService = new FranchiseeService(
                franchiseeRepository,
                franchiseeMapper,
                staffMapper,
                staffService,
                passwordEncoder,
                suburbService,
                suburbMapper,
                orderRepository,
                orderMapper
        );
    }

    @Test
    void shouldCreateStaffAndFranchiseeGetDto() {

        Franchisee franchisee = FranchiseeTestHelper.createFranchiseeWithId();
        StaffGetDto staffGetDto = StaffTestHelper.createStaffGetDto();

        FranchiseePostDto franchiseePostDto = FranchiseeTestHelper.createFranchiseePostDto();
        StaffPostDto staffPostDto = StaffTestHelper.createStaffPostDto();

        when(franchiseeRepository.save(any())).thenReturn(franchisee);
        when(staffService.createStaff(any())).thenReturn(staffGetDto);

        FranchiseeAndStaffDto franchiseeAndStaffDto = franchiseeService.createFranchiseeAndStaff(franchiseePostDto, staffPostDto);
        assertEquals(1234L, franchiseeAndStaffDto.getFranchiseeGetDto().getFranchiseeId());
    }

    @Test
    void shouldAddSuburbListGetDto() {

        Franchisee franchisee = FranchiseeTestHelper.createFranchiseeWithId();
        List<Suburb> suburbsListWithFranchisee = SuburbTestHelper.createSuburbsListWithFranchisee();
        Franchisee franchiseeWithDutyAreas = FranchiseeTestHelper.createFranchiseeWithDutyAreas();
        SuburbListPostDto suburbListPostDto = SuburbTestHelper.createSuburbListPostDto();

        Optional<Franchisee> optionalFranchisee = FranchiseeTestHelper.createOptionalFranchisee();

        when(franchiseeRepository.findFranchiseeById(any())).thenReturn(optionalFranchisee);
        when(suburbService.findSuburbBySscCodes(any())).thenReturn(suburbsListWithFranchisee);
        doNothing().when(optionalFranchisee.orElse(null)).addDutyAreas(suburbsListWithFranchisee);
        when(franchiseeRepository.save(any())).thenReturn(franchiseeWithDutyAreas);

        SuburbListGetDto suburbListGetDto = franchiseeService.addDutyAreas(suburbListPostDto, franchisee.getId());
        assertEquals(12287L, suburbListGetDto.getSuburbs().get(1).getSscCode());
    }


    @Test
    void shouldReturnTrueWhenResourceAlreadyExist() {

        String franchiseeAbn = "124";

        when(franchiseeRepository.existsFranchiseeByAbn(franchiseeAbn))
                .thenReturn(true);

        assertTrue(franchiseeService.franchiseeExists(franchiseeAbn));
    }

    @Test
    void shouldThrowResourceAlreadyExistException() {


        FranchiseePostDto franchiseePostDto = FranchiseeTestHelper.createFranchiseePostDto();
        StaffPostDto staffPostDto = StaffTestHelper.createStaffPostDto();

        when(franchiseeRepository.existsFranchiseeByAbn(any()))
                .thenReturn(true);

        assertThrows(ResourceAlreadyExistException.class,
                () -> franchiseeService.createFranchiseeAndStaff(franchiseePostDto, staffPostDto));

    }

    @Test
    void shouldThrowResourceNotFoundExist() {
        SuburbListPostDto suburbListPostDto = SuburbTestHelper.createSuburbListPostDto();

        when(franchiseeRepository.findFranchiseeById(any()))
                .thenReturn(empty());
        assertThrows(ResourceNotFoundException.class, () -> franchiseeService.addDutyAreas(suburbListPostDto, 6L));

    }

    @Test
    void shouldThrowOrderListGetDto() {
        List<Order> orders = OrderTestHelper.OrderList();
        List<Order> acceptedOrders = OrderTestHelper.AcceptedOrderList();
        OrderListPostDto orderListPostDto = OrderTestHelper.createOrderListPostDto();
        when(orderRepository.findByIdIn(any())).thenReturn(orders);
        when(orderRepository.saveAll(any())).thenReturn(acceptedOrders);
        OrderListGetDto orderListGetDto = franchiseeService.acceptOrders(orderListPostDto);
        assertEquals(OrderStatus.ASSIGNED, orderListGetDto.getOrders().get(0).getStatus());
    }

    @Test
    void shouldThrowOrderNotFoundException() {
        OrderListPostDto orderListPostDto = OrderTestHelper.createEmptyOrderListPostDto();
        when(orderRepository.findByIdIn(any()))
                .thenReturn(new ArrayList<>());
        assertThrows(ResourceNotFoundException.class,
                () -> franchiseeService.acceptOrders(orderListPostDto));
    }

}