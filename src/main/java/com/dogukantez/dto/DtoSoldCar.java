package com.dogukantez.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DtoSoldCar extends DtoBase {
    @NotNull
    private DtoCustomer customer;
    @NotNull
    private DtoGallerist gallerist;
    @NotNull
    private DtoCar car;
}
