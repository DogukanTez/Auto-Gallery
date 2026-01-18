package com.dogukantez.controller;

import com.dogukantez.dto.DtoCustomer;
import com.dogukantez.dto.DtoCustomerIU;

public interface IRestCustomerController {
    public RootEntity<DtoCustomer> saveCustomer(DtoCustomerIU dtoCustomerIU);
}
