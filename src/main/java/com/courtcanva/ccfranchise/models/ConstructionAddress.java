package com.courtcanva.ccfranchise.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ConstructionAddress {

    private String country;

    private String state;

    private String city;

    private String line1;

    private String line2;

    private String postalCode;

}
