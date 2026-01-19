package com.dogukantez.service;

import com.dogukantez.dto.DtoGallerist;
import com.dogukantez.dto.DtoGalleristIU;

public interface IGalleristService {
    public DtoGallerist saveGallerist(DtoGalleristIU dtoGalleristIU);
}
