package com.example.ordersystem.user.domain.repository;

import com.example.ordersystem.user.domain.User;

import java.util.Optional;

public interface UserRepository {

    User save(User user);

    Optional<User> findById(Long id);
}
