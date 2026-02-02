package com.example.ordersystem.user.presentation;

import com.example.ordersystem.user.application.dto.request.CreateUserRequest;
import com.example.ordersystem.user.domain.service.UserService;
import com.example.ordersystem.user.presentation.docs.UserApiDocs;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserApiController implements UserApiDocs {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<Long> registerUser(@Valid @RequestBody CreateUserRequest request) {
        Long userId = userService.registerUser(request.toCommand());
        return ResponseEntity.ok(userId);
    }
}
