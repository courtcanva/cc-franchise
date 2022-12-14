package com.courtcanva.ccfranchise.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class DesignInformation {

    private String quotation;

    private String constructionDraw;

    private Boolean isNeedLevelGround;

    private ConstructionAddress constructionAddress;

    private Design design;

    private List<QuotationDetails> quotationDetails;

}
