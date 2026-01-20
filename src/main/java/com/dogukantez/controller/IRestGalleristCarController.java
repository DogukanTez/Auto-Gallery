package com.dogukantez.controller;

import com.dogukantez.dto.DtoGalleristCar;
import com.dogukantez.dto.DtoGalleristCarIU;
import com.dogukantez.dto.DtoGalleristIU;

public interface IRestGalleristCarController {
    public RootEntity<DtoGalleristCar> saveGalleristCar(DtoGalleristCarIU dtoGalleristCarIU);
}
