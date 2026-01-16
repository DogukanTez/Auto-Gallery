package com.dogukantez.controller;


import com.dogukantez.dto.DtoAccount;
import com.dogukantez.dto.DtoAccountIU;

public interface IRestAccountController {
    public RootEntity<DtoAccount> saveAccount(DtoAccountIU dtoAccountIU);
}
