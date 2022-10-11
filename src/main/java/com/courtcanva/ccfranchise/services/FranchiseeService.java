package com.courtcanva.ccfranchise.services;

import com.courtcanva.ccfranchise.dtos.FranchiseeAndStaffDto;
import com.courtcanva.ccfranchise.dtos.FranchiseePostDto;
import com.courtcanva.ccfranchise.dtos.OpenOrderDto;
import com.courtcanva.ccfranchise.dtos.StaffGetDto;
import com.courtcanva.ccfranchise.dtos.StaffPostDto;
import com.courtcanva.ccfranchise.exceptions.NoAvailableOrderException;
import com.courtcanva.ccfranchise.exceptions.ResourceAlreadyExistException;
import com.courtcanva.ccfranchise.mappers.FranchiseeMapper;
import com.courtcanva.ccfranchise.mappers.OrderDisplayMapper;
import com.courtcanva.ccfranchise.mappers.StaffMapper;
import com.courtcanva.ccfranchise.models.Franchisee;
import com.courtcanva.ccfranchise.models.Order;
import com.courtcanva.ccfranchise.models.Staff;
import com.courtcanva.ccfranchise.repositories.FranchiseeRepository;
import com.courtcanva.ccfranchise.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class FranchiseeService {
    private final FranchiseeRepository franchiseeRepository;

    private final OrderRepository orderRepository;
    private final FranchiseeMapper franchiseeMapper;

    private final StaffMapper staffMapper;
    private final OrderDisplayMapper orderDisplayMapper;

    private final StaffService staffService;

    @Transactional
    public FranchiseeAndStaffDto createFranchiseeAndStaff(FranchiseePostDto franchiseePostDto, StaffPostDto staffPostDto) {

        if (checkFranchiseeIsExist(franchiseePostDto.getAbn())) {

            log.error("franchisee with abn: {} already exist",franchiseePostDto.getAbn());

            throw new ResourceAlreadyExistException("franchisee already exist");

        }

        Franchisee franchisee = franchiseeRepository
                .save(franchiseeMapper.postDtoToFranchisee(franchiseePostDto));

        Staff staff = staffMapper.postDtoToStaff(staffPostDto);
        staff.setFranchisee(franchisee);

        StaffGetDto staffGetDto = staffService.createStaff(staff);

        return FranchiseeAndStaffDto.builder()
                .staffGetDto(staffGetDto)
                .franchiseeGetDto(franchiseeMapper.franchiseeToGetDto(franchisee))
                .build();
    }

    public boolean checkFranchiseeIsExist(String abn) {

        return franchiseeRepository.existsFranchiseeByAbn(abn);

    }

    public List<OpenOrderDto> getOpenOrders(Long id) {
        Optional<Franchisee> franchiseeFromDatabase = franchiseeRepository.findById(id);
        if (franchiseeFromDatabase.isEmpty()) {
            throw new NoAvailableOrderException("no available orders because of invalid franchisee");
        }
        Franchisee franchisee = franchiseeFromDatabase.orElse(null);

        List<Order> all = orderRepository.findFirst10ByFranchiseeId(franchisee.getId());
        if (all.size() == 0) {
            throw new NoAvailableOrderException("no available orders");
        }
            return all.stream().map(orderDisplayMapper::orderToDto).toList();

    }
}
