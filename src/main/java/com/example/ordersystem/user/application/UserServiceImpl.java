package com.example.ordersystem.user.application;

import com.example.ordersystem.user.application.dto.command.CreateUserCommand;
import com.example.ordersystem.user.domain.User;
import com.example.ordersystem.user.domain.repository.UserRepository;
import com.example.ordersystem.user.domain.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public Long registerUser(CreateUserCommand command) {
        User user = command.toEntity();
        return userRepository.save(user).getId();
    }
}
