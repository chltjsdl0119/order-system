package com.example.ordersystem.user.presentation.docs;

import com.example.ordersystem.user.application.dto.request.CreateUserRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;

public interface UserApiDocs {

    @Operation(
            summary = "회원 가입",
            description = "신규 사용자를 등록한다."
    )
    @ApiResponse(
            responseCode = "200",
            description = "회원 가입 성공",
            content = @Content(
                    schema = @Schema(implementation = Long.class)
            )
    )
    ResponseEntity<Long> registerUser(
            @Parameter(
                    description = "회원 가입 요청 DTO",
                    required = true
            )
            CreateUserRequest request
    );
}
