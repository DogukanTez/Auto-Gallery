package com.dogukantez.controller;

import com.dogukantez.dto.DtoGallerist;
import com.dogukantez.dto.DtoGalleristIU;

public interface IRestGalleristController {
    public RootEntity<DtoGallerist> saveGallerist(DtoGalleristIU dtoGalleristIU);
}
