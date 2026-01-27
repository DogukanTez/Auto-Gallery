package com.dogukantez.service.impl;

import com.dogukantez.exception.BaseException;
import com.dogukantez.exception.ErrorMessage;
import com.dogukantez.exception.MessageType;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import com.dogukantez.dto.CurrencyRatesResponse;
import com.dogukantez.service.ICurrencyRatesService;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CurrencyRatesServiceImpl implements ICurrencyRatesService {
    @Override
    public CurrencyRatesResponse getCurrencyRates(String startDate, String endDate) {
        String rootURL = "https://evds2.tcmb.gov.tr/service/evds/";
        String series = "TP.DK.USD.A";
        String type="json";
        String endpoint = rootURL+"series="+series+"&startDate="+startDate+"&endDate="+endDate+"&type="+type;

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("key","XXXXXX");
        //httpHeaders.set("Content-Type", "application/json");
        HttpEntity<?> httpEntity= new HttpEntity<>(null,httpHeaders);
        RestTemplate restTemplate = new RestTemplate();

        try {
            ResponseEntity<CurrencyRatesResponse> response = restTemplate.exchange(endpoint, HttpMethod.GET, httpEntity, new ParameterizedTypeReference<CurrencyRatesResponse>() {
            });


            if(response.getStatusCode().is2xxSuccessful()){
                return response.getBody();
            }


        } catch (Exception e) {
            throw new BaseException(new ErrorMessage(MessageType.CURRENCY_RATES_IS_ERR_OCCURRED,e.getMessage()));
        }
            return null;

    }
}
