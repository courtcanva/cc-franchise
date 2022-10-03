package com.courtcanva.ccfranchise.dtos.suburbs;


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

    private List<SuburbGetDto> suburbs;

}
