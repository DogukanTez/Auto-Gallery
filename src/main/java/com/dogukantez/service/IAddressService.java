package com.dogukantez.service;

import com.dogukantez.dto.DtoAddress;
import com.dogukantez.dto.DtoAddressIU;

public interface IAddressService {
public DtoAddress saveAddress(DtoAddressIU dtoAddressIU);
public DtoAddress updateAddress(Long id,DtoAddressIU dtoAddressIU);
}
