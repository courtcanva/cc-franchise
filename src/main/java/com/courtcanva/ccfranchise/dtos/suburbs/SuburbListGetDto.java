package com.courtcanva.ccfranchise.dtos.suburbs;


import com.courtcanva.ccfranchise.constants.DutyAreaFilterMode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SuburbListGetDto {

    private DutyAreaFilterMode filterMode;

    private List<SuburbGetDto> suburbs;

}
