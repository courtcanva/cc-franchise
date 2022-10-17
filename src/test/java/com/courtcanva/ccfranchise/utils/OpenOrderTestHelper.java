package com.courtcanva.ccfranchise.utils;

import com.courtcanva.ccfranchise.dtos.IdDto;
import com.courtcanva.ccfranchise.dtos.suburbs.SuburbListPostDto;

public class OpenOrderTestHelper {
    public static IdDto createIdDto(Long mockFranchiseeId) {
        return new IdDto(mockFranchiseeId);
    }
}
