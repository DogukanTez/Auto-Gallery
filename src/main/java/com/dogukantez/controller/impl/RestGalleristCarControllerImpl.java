package com.dogukantez.controller.impl;

import com.dogukantez.controller.IRestGalleristCarController;
import com.dogukantez.controller.RestBaseController;
import com.dogukantez.controller.RootEntity;
import com.dogukantez.dto.DtoGalleristCar;
import com.dogukantez.dto.DtoGalleristCarIU;
import com.dogukantez.dto.DtoGalleristIU;
import com.dogukantez.service.IGalleristCarService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/api/gallerist-car")
public class RestGalleristCarControllerImpl extends RestBaseController implements IRestGalleristCarController {
    private final IGalleristCarService galleristCarService;

    public RestGalleristCarControllerImpl(IGalleristCarService galleristCarService) {
        this.galleristCarService = galleristCarService;
    }

    @Override
    @RequestMapping("/save")
    public RootEntity<DtoGalleristCar> saveGalleristCar(@Valid @RequestBody DtoGalleristCarIU dtoGalleristCarIU) {
        return ok(galleristCarService.saveGalleristCar(dtoGalleristCarIU));
    }
}
