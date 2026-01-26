package com.dogukantez.controller;

import com.dogukantez.dto.CurrencyRatesResponse;

public interface IRestCurrencyRatesController {
    RootEntity<CurrencyRatesResponse> getCurrencyRates(String startDate, String endDate);
}
