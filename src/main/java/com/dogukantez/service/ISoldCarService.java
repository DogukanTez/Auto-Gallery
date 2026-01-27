package com.dogukantez.service;

import com.dogukantez.dto.DtoSoldCar;
import com.dogukantez.dto.DtoSoldCarIU;

public interface ISoldCarService {
    public DtoSoldCar buyCar(DtoSoldCarIU dtoSoldCarIU );
}
