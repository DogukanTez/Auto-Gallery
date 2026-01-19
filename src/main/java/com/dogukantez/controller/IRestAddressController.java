package com.dogukantez.controller;

import com.dogukantez.dto.DtoAddress;
import com.dogukantez.dto.DtoAddressIU;

public interface IRestAddressController {
    public RootEntity<DtoAddress> saveAddress(DtoAddressIU dtoAddressIU);
    public RootEntity<DtoAddress> updateAddress(Long id,DtoAddressIU dtoAddressIU);
}
