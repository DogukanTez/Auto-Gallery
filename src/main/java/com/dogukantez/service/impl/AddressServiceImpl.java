package com.dogukantez.service.impl;

import com.dogukantez.dto.DtoAddress;
import com.dogukantez.dto.DtoAddressIU;
import com.dogukantez.entities.Address;
import com.dogukantez.exception.BaseException;
import com.dogukantez.exception.ErrorMessage;
import com.dogukantez.repository.AddressRepository;
import com.dogukantez.service.IAddressService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AddressServiceImpl implements IAddressService {
    @Autowired
    public AddressRepository addressRepository;

    public Address createAddress(DtoAddressIU dtoAddressIU){
            Address address = new Address();
            address.setCreateTime(new Date());
            BeanUtils.copyProperties(dtoAddressIU,address);
            return address;
    }

    @Override
    public DtoAddress saveAddress(DtoAddressIU dtoAddressIU) {
        DtoAddress dtoAddress=new DtoAddress();
        Address savedAddress = addressRepository.save(createAddress(dtoAddressIU));
        BeanUtils.copyProperties(savedAddress,dtoAddress);
        return dtoAddress;

    }
}
