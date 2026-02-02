package com.example.ordersystem.user.domain.repository;

import com.example.ordersystem.user.domain.User;

public interface UserRepository {

    User save(User user);
}
