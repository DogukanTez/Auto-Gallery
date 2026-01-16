package com.dogukantez.service;

import com.dogukantez.dto.DtoAccount;
import com.dogukantez.dto.DtoAccountIU;

public interface IAccountService {
    public DtoAccount saveAccount(DtoAccountIU dtoAccountIU);
}
