package com.courtcanva.ccfranchise.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CourtSize {

    private String name;

    private int length;

    private int width;

    private int centreCircleRadius;

    private int threePointRadius;

    private int threePointLine;

    private int lengthOfCorner;

    private int restrictedAreaLength;

    private int restrictedAreaWidth;

    private int sideBorderWidth;

    private int lineBorderWidth;

}
