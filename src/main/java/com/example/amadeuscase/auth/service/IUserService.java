package com.example.amadeuscase.auth.service;

import com.example.amadeuscase.auth.entity.AmdUser;
import com.example.amadeuscase.auth.model.request.UserCreateDto;
import com.example.amadeuscase.auth.model.request.UserLoginDto;

public interface IUserService {
    public AmdUser login(UserLoginDto userLoginDto);
    public void register(UserCreateDto userCreateDto);
}
