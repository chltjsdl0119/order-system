package com.example.ordersystem.user.infrastructure;

import com.example.ordersystem.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<User, Long> {
}
