package com.dogukantez.controller.impl;

import com.dogukantez.controller.IRestCurrencyRatesController;
import com.dogukantez.controller.RestBaseController;
import com.dogukantez.controller.RootEntity;
import com.dogukantez.dto.CurrencyRatesResponse;
import com.dogukantez.service.ICurrencyRatesService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("rest/api/")
public class RestCurrencyRatesControllerImpl extends RestBaseController implements IRestCurrencyRatesController {
    private final ICurrencyRatesService currencyRatesService;

    public RestCurrencyRatesControllerImpl (ICurrencyRatesService currencyRatesService){
        this.currencyRatesService=currencyRatesService;
    }


    @GetMapping("/currency-rates")
    @Override
    public RootEntity<CurrencyRatesResponse> getCurrencyRates(@RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate) {
        return ok(currencyRatesService.getCurrencyRates(startDate, endDate));
    }
}
