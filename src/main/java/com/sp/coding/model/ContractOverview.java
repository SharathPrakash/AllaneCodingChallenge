package com.sp.coding.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class ContractOverview {
    private String contractNo;
    private String customerName;
    private String vehicleSummary;
    private String vin;
    private Double monthlyRate;
    private Double vehiclePrice;
    private String contractDetailsLink;
}
