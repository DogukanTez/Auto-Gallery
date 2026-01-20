package com.dogukantez.controller.impl;

import com.dogukantez.controller.IRestCarController;
import com.dogukantez.controller.RestBaseController;
import com.dogukantez.controller.RootEntity;
import com.dogukantez.dto.DtoCar;
import com.dogukantez.dto.DtoCarIU;
import com.dogukantez.service.ICarService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/api/car")
public class RestCarControllerImpl extends RestBaseController implements IRestCarController {
    public final ICarService carService;

    public RestCarControllerImpl(ICarService carService) {
        this.carService = carService;
    }

    @Override
    @PostMapping("/save")
    public RootEntity<DtoCar> saveCar(@Valid @RequestBody DtoCarIU dtoCarIU) {
        return ok(carService.saveCar(dtoCarIU));
    }
}
