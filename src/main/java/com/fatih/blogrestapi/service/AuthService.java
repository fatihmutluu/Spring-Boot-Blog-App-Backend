package com.fatih.blogrestapi.service;

import com.fatih.blogrestapi.dto.LoginDto;
import com.fatih.blogrestapi.dto.RegisterDto;

public interface AuthService {
    String login(LoginDto loginDto);

    String register(RegisterDto registerDto);
}
