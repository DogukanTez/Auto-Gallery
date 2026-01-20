package com.dogukantez.dto;

import com.dogukantez.enums.CarStatusType;
import com.dogukantez.enums.CurrencyType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DtoCar extends DtoBase {
    private String plate;

    private String brand;

    private String model;

    private int productionYear;

    private BigDecimal price;

    private CurrencyType currencyType;

    private BigDecimal damagePrice;

    private CarStatusType carStatusType;
}
