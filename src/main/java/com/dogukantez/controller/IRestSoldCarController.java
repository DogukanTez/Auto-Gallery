package com.dogukantez.controller;

import com.dogukantez.dto.DtoSoldCar;
import com.dogukantez.dto.DtoSoldCarIU;

public interface IRestSoldCarController {
    public RootEntity<DtoSoldCar> buyCar(DtoSoldCarIU dtoSoldCarIU);
}
