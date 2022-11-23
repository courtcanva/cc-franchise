package com.courtcanva.ccfranchise.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class DesignInformation {

    private String quotation;

    private String constructionDraw;

    private Boolean isNeedLevelGround;

    private ConstructionAddress constructionAddress;

}
