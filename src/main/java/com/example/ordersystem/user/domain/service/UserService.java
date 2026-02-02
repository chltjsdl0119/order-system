package com.example.ordersystem.user.domain.service;

import com.example.ordersystem.user.application.dto.command.CreateUserCommand;

public interface UserService {

    Long registerUser(CreateUserCommand command);
}
