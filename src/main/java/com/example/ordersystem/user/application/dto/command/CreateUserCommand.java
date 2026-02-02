package com.example.ordersystem.user.application.dto.command;

import com.example.ordersystem.user.domain.User;

public record CreateUserCommand(
        String name
) {

    public User toEntity() {
        return User.builder()
                .name(name)
                .build();
    }
}
