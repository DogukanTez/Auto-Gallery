package com.dogukantez.service.impl;

import com.dogukantez.dto.AuthRequest;
import com.dogukantez.dto.DtoUser;
import com.dogukantez.entities.User;
import com.dogukantez.repository.UserRepository;
import com.dogukantez.service.IAuthenticationService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AuthenticationServiceImpl implements IAuthenticationService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

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
}
