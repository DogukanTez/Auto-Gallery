package com.dogukantez.controller.impl;

import com.dogukantez.controller.IRestSoldCarController;
import com.dogukantez.controller.RestBaseController;
import com.dogukantez.controller.RootEntity;
import com.dogukantez.dto.DtoSoldCar;
import com.dogukantez.dto.DtoSoldCarIU;
import com.dogukantez.service.ISoldCarService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("rest/api/sold-car")
public class RestSoldCarControllerImpl extends RestBaseController implements IRestSoldCarController {

    private final ISoldCarService soldCarService;

    public RestSoldCarControllerImpl(ISoldCarService soldCarService){
        this.soldCarService= soldCarService;
    }

    @PostMapping("/save")
    @Override
    public RootEntity<DtoSoldCar> buyCar(@Valid @RequestBody DtoSoldCarIU dtoSoldCarIU) {
        return ok(soldCarService.buyCar(dtoSoldCarIU));
    }
}
