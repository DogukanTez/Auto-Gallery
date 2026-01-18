package com.dogukantez.service;

import com.dogukantez.dto.DtoCustomer;
import com.dogukantez.dto.DtoCustomerIU;

public interface ICustomerService {
    public DtoCustomer saveCustomer(DtoCustomerIU dtoCustomerIU);
}
