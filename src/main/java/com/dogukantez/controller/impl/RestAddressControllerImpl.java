package com.dogukantez.controller.impl;

import com.dogukantez.controller.IRestAddressController;
import com.dogukantez.controller.RestBaseController;
import com.dogukantez.controller.RootEntity;
import com.dogukantez.dto.DtoAddress;
import com.dogukantez.dto.DtoAddressIU;
import com.dogukantez.service.IAddressService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest/api/address")
public class RestAddressControllerImpl extends RestBaseController implements IRestAddressController {
    private final IAddressService addressService;

    public RestAddressControllerImpl(IAddressService addressService) {
        this.addressService = addressService;
    }

    @PostMapping("/save")
    @Override
    public RootEntity<DtoAddress> saveAddress(@Valid @RequestBody DtoAddressIU dtoAddressIU) {
            return ok(addressService.saveAddress(dtoAddressIU));
    }

    @PutMapping("/{id}")
    @Override
    public RootEntity<DtoAddress> updateAddress(@PathVariable Long id,@Valid @RequestBody DtoAddressIU dtoAddressIU) {
        return ok(addressService.updateAddress(id, dtoAddressIU));
    }
}
