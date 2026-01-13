package com.dogukantez.service;


import com.dogukantez.dto.AuthRequest;
import com.dogukantez.dto.DtoUser;

public interface IAuthenticationService {
    public DtoUser register (AuthRequest input);
}
