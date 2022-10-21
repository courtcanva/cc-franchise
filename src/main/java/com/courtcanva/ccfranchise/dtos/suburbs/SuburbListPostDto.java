package com.courtcanva.ccfranchise.dtos.suburbs;

import com.courtcanva.ccfranchise.constants.DutyAreaFilterMode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SuburbListPostDto {

    @NotNull
    private DutyAreaFilterMode filterMode;

    private List<SuburbPostDto> suburbs;
}
