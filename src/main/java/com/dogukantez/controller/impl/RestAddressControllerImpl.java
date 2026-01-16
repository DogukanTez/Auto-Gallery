package com.dogukantez.controller.impl;

import com.dogukantez.controller.IRestAddressController;
import com.dogukantez.controller.RestBaseController;
import com.dogukantez.controller.RootEntity;
import com.dogukantez.dto.DtoAddress;
import com.dogukantez.dto.DtoAddressIU;
import com.dogukantez.service.IAddressService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/api/address")
public class RestAddressControllerImpl extends RestBaseController implements IRestAddressController {
    @Autowired
    private IAddressService addressService;

    @PostMapping("save")
    @Override
    public RootEntity<DtoAddress> saveAddress(@Valid @RequestBody DtoAddressIU dtoAddressIU) {
            return ok(addressService.saveAddress(dtoAddressIU));
    }
}
