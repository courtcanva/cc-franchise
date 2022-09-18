package com.courtcanva.ccfranchise.controllers;

//@SpringBootTest
//@AutoConfigureMockMvc
//@ActiveProfiles("test")
//class FranchiseeControllerTest {
//    @Resource
//    private MockMvc mockMvc;
//
//    @Mock
//    private StaffService staffService;
//
//    @Mock
//    private FranchiseeService franchiseService;
//
//    @InjectMocks
//    private FranchiseeController franchiseController;
//
//    @BeforeEach
//    public void setUp() {
//
//        mockMvc = MockMvcBuilders.standaloneSetup(franchiseController).build();
//
//    }
//
//    @Test
//    void shouldReturnStaffAndFranchiseId() throws Exception {
//
//        FranchiseeDto franchiseDto = FranchiseeDto.builder()
//                .businessName("AAAAA")
//                .businessAddress("zetland NSWssss")
//                .ABN(1231232)
//                .build();
//        StaffInfoDto staffInfoDto = StaffInfoDto.builder()
//                .address("gadsfadsfdsa")
//                .email("baoruoxi@163.com")
//                .state("NSW")
//                .postcode(3213)
//                .password("fdsafsdfdsaf")
//                .phoneNumber("31232131")
//                .firstName("first")
//                .lastName("last")
//                .build();
//        franchiseDto.setStaff(staffInfoDto);
////        when(staffService.createStaffOfFranchisee(any())).thenReturn(12L);
////        when(franchiseService.createFranchisee(any())).thenReturn(21L);
////
////
////        mockMvc.perform(MockMvcRequestBuilders.post("/signup")
////                        .content(new ObjectMapper().writeValueAsString(franchiseDto))
////                        .contentType(MediaType.APPLICATION_JSON_VALUE))
////                .andExpect(status().isCreated());
//
//    }
//}