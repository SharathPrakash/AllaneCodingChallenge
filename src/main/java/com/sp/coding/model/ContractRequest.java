package com.sp.coding.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class ContractRequest {

    private int customerId;
    private int vehicleId;
}
