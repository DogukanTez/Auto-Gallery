package com.dogukantez.controller.impl;

import com.dogukantez.controller.IRestGalleristController;
import com.dogukantez.controller.RestBaseController;
import com.dogukantez.controller.RootEntity;
import com.dogukantez.dto.DtoGallerist;
import com.dogukantez.dto.DtoGalleristIU;
import com.dogukantez.service.IGalleristService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("rest/api/gallerist")
public class RestGalleristController extends RestBaseController implements IRestGalleristController {

    private final IGalleristService galleristService;

    public RestGalleristController(IGalleristService galleristService) {
        this.galleristService = galleristService;
    }

    @PostMapping("/save")
    @Override
    public RootEntity<DtoGallerist> saveGallerist(@Valid @RequestBody DtoGalleristIU dtoGalleristIU) {
        return ok(galleristService.saveGallerist(dtoGalleristIU));
    }
}
