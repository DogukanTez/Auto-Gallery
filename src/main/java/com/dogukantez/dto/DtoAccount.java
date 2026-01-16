package com.dogukantez.dto;

import com.dogukantez.enums.CurrencyType;

import java.math.BigDecimal;

public class DtoAccount extends DtoBase{
    private String accountNo;
    private String iban;
    private BigDecimal amount;
    private CurrencyType currencyType;
}
