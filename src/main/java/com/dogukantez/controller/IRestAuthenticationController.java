package com.dogukantez.controller;

import com.dogukantez.dto.AuthRequest;
import com.dogukantez.dto.AuthResponse;
import com.dogukantez.dto.DtoUser;
import com.dogukantez.dto.RefreshTokenRequest;

public interface IRestAuthenticationController {
    public RootEntity<DtoUser> register(AuthRequest input);
    public RootEntity<AuthResponse> authenticate (AuthRequest input);
    public RootEntity<AuthResponse> refreshToken(RefreshTokenRequest input);
}
