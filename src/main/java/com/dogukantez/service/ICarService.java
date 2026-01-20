package com.dogukantez.service;

import com.dogukantez.dto.DtoCar;
import com.dogukantez.dto.DtoCarIU;

public interface ICarService {
    DtoCar saveCar(DtoCarIU dtoCarIU);
}
