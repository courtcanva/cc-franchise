package com.courtcanva.ccfranchise.dtos.suburbs;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class SuburbListGetDto {

    private List<SuburbGetDto> suburbs;

}
