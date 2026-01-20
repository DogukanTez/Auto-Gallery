package com.dogukantez.controller;

import com.dogukantez.dto.DtoCar;
import com.dogukantez.dto.DtoCarIU;

public interface IRestCarController {
    public RootEntity<DtoCar> saveCar(DtoCarIU dtoCarIU);
}
