package com.dogukantez.service;

import com.dogukantez.dto.CurrencyRatesResponse;

public interface ICurrencyRatesService {
    public CurrencyRatesResponse getCurrencyRates(String startDate, String endDate);
}
