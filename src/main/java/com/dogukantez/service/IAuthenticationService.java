package com.dogukantez.service;


import com.dogukantez.dto.AuthRequest;
import com.dogukantez.dto.AuthResponse;
import com.dogukantez.dto.DtoUser;
import com.dogukantez.dto.RefreshTokenRequest;

public interface IAuthenticationService {
    public DtoUser register (AuthRequest input);
    public AuthResponse authenticate(AuthRequest input);
    public AuthResponse refreshToken(RefreshTokenRequest input);
}
