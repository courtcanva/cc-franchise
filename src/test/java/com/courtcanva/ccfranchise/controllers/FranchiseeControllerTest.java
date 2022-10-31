package com.courtcanva.ccfranchise.controllers;

import com.courtcanva.ccfranchise.constants.AUState;
import com.courtcanva.ccfranchise.dtos.FranchiseeAndStaffPostDto;
import com.courtcanva.ccfranchise.dtos.FranchiseePostDto;
import com.courtcanva.ccfranchise.dtos.StaffPostDto;
import com.courtcanva.ccfranchise.dtos.orders.OrderListPostDto;
import com.courtcanva.ccfranchise.dtos.suburbs.SuburbListAndFilterModePostDto;
import com.courtcanva.ccfranchise.models.Franchisee;
import com.courtcanva.ccfranchise.models.Order;
import com.courtcanva.ccfranchise.repositories.FranchiseeRepository;
import com.courtcanva.ccfranchise.repositories.OrderRepository;
import com.courtcanva.ccfranchise.repositories.StaffRepository;
import com.courtcanva.ccfranchise.repositories.SuburbRepository;
import com.courtcanva.ccfranchise.utils.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.Optional;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc(addFilters = false)
class FranchiseeControllerTest {

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private FranchiseeRepository franchiseeRepository;
    @Autowired
    private StaffRepository staffRepository;
    @Autowired
    private FranchiseeController franchiseeController;
    @Autowired
    private SuburbRepository suburbRepository;
    @MockBean
    private MailingClient mailingClient;
    @Autowired
    private OrderRepository orderRepository;

    @BeforeEach
    public void clear() {

        staffRepository.deleteAll();
        orderRepository.deleteAll();
        franchiseeRepository.deleteAll();

    }

    @Test
    void givenFranchiseeAndStaffPostDto_whenSignUpFranchisee_shouldReturnStaffAndFranchise() throws Exception {
        FranchiseeAndStaffPostDto franchiseeAndStaffPostDto = FranchiseeAndStaffTestHelper.createFranchiseeAndStaffPostDto();

        mockMvc.perform(MockMvcRequestBuilders.post("/franchisee/signup")
                        .content(objectMapper.writeValueAsString(franchiseeAndStaffPostDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.staffGetDto.email").value("baoruoxi@163.com"))
                .andExpect(jsonPath("$.franchiseeGetDto.abn").value("12312123111"));

    }

    @Test
    @WithMockUser
    void givenListOfServiceArea_whenAddDutyArea_shouldReturnSelectSuburbs() throws Exception {
        Long mockFranchiseeId = franchiseeController.signUpFranchiseeAndStaff(new FranchiseeAndStaffPostDto(new FranchiseePostDto("CourtCanva", "CourtCanva LTD", "12312123111", "23468290381", "Melbourne", AUState.VIC, 3000), new StaffPostDto("Taylor", "Swift", "taylor.s@gmail.com", "123456789", "abc st", 3000, AUState.VIC, "sdjkhsd"))).getFranchiseeGetDto().getFranchiseeId();
        suburbRepository.save(SuburbTestHelper.suburb1());
        suburbRepository.save(SuburbTestHelper.suburb2());

        SuburbListAndFilterModePostDto suburbListAndFilterModePostDto = SuburbTestHelper.createSuburbListPostDtoWithIncludeMode();

        mockMvc.perform(MockMvcRequestBuilders.post("/franchisee/" + mockFranchiseeId.toString() + "/service_areas")
                        .content(objectMapper.writeValueAsString(suburbListAndFilterModePostDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(jsonPath("$.suburbs[0].sscCode").value(11344L))
                .andExpect(jsonPath("$.suburbs[1].sscCode").value(12287L));

    }

    @Test
    void shouldReturnAcceptOrders() throws Exception {
        Long mockFranchiseeId = franchiseeController.signUpFranchiseeAndStaff(
                        new FranchiseeAndStaffPostDto(
                                new FranchiseePostDto("CourtCanva", "CourtCanva LTD", "12312123111", "23468290381", "Melbourne", AUState.VIC, 3000),
                                new StaffPostDto("Taylor", "Swift", "taylor.s@gmail.com", "123456789", "abc st", 3000, AUState.VIC, "sdjkhsd")))
                .getFranchiseeGetDto().getFranchiseeId();

        orderRepository.save(OrderTestHelper.Order1());
        OrderListPostDto orderListPostDto = OrderTestHelper.createOrderListPostDto();

        mockMvc.perform(MockMvcRequestBuilders.post("/franchisee/" + mockFranchiseeId.toString() + "/accept_orders")
                        .content(objectMapper.writeValueAsString(orderListPostDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.orders[0].status")
                        .value("ACCEPTED"));

    }

    @Test
    @WithMockUser
    void shouldReturnAcceptedOrderWithPagination() throws Exception {

        Order order = orderRepository.save(OrderTestHelper.mockAcceptedOrder1());
        Franchisee franchisee = franchiseeRepository.save(FranchiseeTestHelper.createFranchisee());
        // Optional<Order> order1 = orderRepository.findById(1L);
        order.setFranchisee(franchisee);
        orderRepository.save(order);

        mockMvc.perform(MockMvcRequestBuilders.get("/franchisee/"+franchisee.getId()+"/acceptedorders?page=1"))
                .andExpect(jsonPath("$.acceptedOrders[0].orderId").value("111"));

    }
}