package com.dogukantez.service.impl;

import com.dogukantez.dto.AuthRequest;
import com.dogukantez.dto.AuthResponse;
import com.dogukantez.dto.DtoUser;
import com.dogukantez.entities.RefreshToken;
import com.dogukantez.entities.User;
import com.dogukantez.exception.BaseException;
import com.dogukantez.exception.ErrorMessage;
import com.dogukantez.exception.MessageType;
import com.dogukantez.jwt.JWTService;
import com.dogukantez.repository.RefreshTokenRepository;
import com.dogukantez.repository.UserRepository;
import com.dogukantez.service.IAuthenticationService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuthenticationServiceImpl implements IAuthenticationService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationProvider authenticationProvider;
    @Autowired
    private JWTService jwtService;
    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    private RefreshToken createRefreshToken(User user){
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setCreateTime(new Date());
        refreshToken.setExpireDate(new Date(System.currentTimeMillis() + 1000*60*60*4));
        refreshToken.setRefreshToken(UUID.randomUUID().toString());
        refreshToken.setUser(user);
        return refreshToken;
    }

    public User createUser(AuthRequest input){
        User user = new User();
        user.setUsername(input.getUsername());
        user.setCreateTime(new Date());
        user.setPassword(passwordEncoder.encode(input.getPassword()));

        return user;

    }

    @Override
    public DtoUser register(AuthRequest input) {
        DtoUser dtoUser =new DtoUser();


        User savedUser = createUser(input);
        userRepository.save(savedUser);

        BeanUtils.copyProperties(savedUser,dtoUser);
        return dtoUser;
    }

    @Override
    public AuthResponse authenticate(AuthRequest input) {

        try {
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(input.getUsername(),input.getPassword());

            authenticationProvider.authenticate(authenticationToken);

            Optional<User> optUser =  userRepository.findByUsername(input.getUsername());

            String accessToken = jwtService.generateToken(optUser.get());
            RefreshToken refreshToken = createRefreshToken(optUser.get());
            RefreshToken savedRefreshToken = refreshTokenRepository.save(refreshToken);

            return new AuthResponse(accessToken,savedRefreshToken.getRefreshToken());

        } catch (Exception e) {
            throw new BaseException(new ErrorMessage(MessageType.USERNAME_OR_PASSWORD_INCORRECT,e.getMessage()));
        }

    }
}
