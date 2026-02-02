package com.example.ordersystem.user.application.dto.request;

import com.example.ordersystem.user.application.dto.command.CreateUserCommand;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateUserRequest(
        @NotBlank
        @Size(max = 10, message = "이름은 10자를 초과할 수 없습니다.")
        String name
) {

    public CreateUserCommand toCommand() {
        return new CreateUserCommand(
                name
        );
    }
}
