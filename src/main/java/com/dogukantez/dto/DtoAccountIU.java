package com.dogukantez.dto;

import com.dogukantez.enums.CurrencyType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class DtoAccountIU {
    @NotEmpty
    private String accountNo;
    @NotEmpty
    private String iban;
    @NotEmpty
    private BigDecimal amount;
    @NotEmpty
    private CurrencyType currencyType;
}
