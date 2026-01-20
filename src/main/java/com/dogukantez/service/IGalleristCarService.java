package com.dogukantez.service;

import com.dogukantez.dto.DtoGalleristCar;
import com.dogukantez.dto.DtoGalleristCarIU;

public interface IGalleristCarService {
    public DtoGalleristCar saveGalleristCar(DtoGalleristCarIU dtoGalleristCarIU);
}
