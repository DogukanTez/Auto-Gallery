package com.dogukantez.dto;

import com.dogukantez.enums.CarStatusType;
import com.dogukantez.enums.CurrencyType;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;


import java.math.BigDecimal;
@Getter
@Setter
public class DtoCarIU {
    @NotNull
    private String plate;
    @NotNull
    private String brand;
    @NotNull
    private String model;
    @NotNull
    private int productionYear;
    @NotNull
    private BigDecimal price;
    @NotNull
    private CurrencyType currencyType;
    @NotNull
    private BigDecimal damagePrice;
    @NotNull
    private CarStatusType carStatusType;
}
