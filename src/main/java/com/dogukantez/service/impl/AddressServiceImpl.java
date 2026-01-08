package com.dogukantez.service.impl;

import com.dogukantez.exception.BaseException;
import com.dogukantez.exception.ErrorMessage;
import com.dogukantez.service.IAddressService;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements IAddressService {

    public void test(){
        throw new BaseException(new ErrorMessage(null,null));
    }
}
