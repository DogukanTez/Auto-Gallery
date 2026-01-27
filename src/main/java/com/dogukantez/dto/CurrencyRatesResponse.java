package com.dogukantez.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CurrencyRatesResponse {
    @JsonProperty("totalCount")
    private Integer totalCount;
    @JsonProperty("items")
    private List<CurrencyRatesItems> items;
}
