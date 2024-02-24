package com.project.webtoonzoa.controller;

import com.project.webtoonzoa.dto.CommonResponse;
import com.project.webtoonzoa.dto.UserRequestDto;
import com.project.webtoonzoa.global.util.JwtUtil;
import com.project.webtoonzoa.global.util.UserDetailsImpl;
import com.project.webtoonzoa.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<CommonResponse<Long>> createUser(
        @Valid @RequestBody UserRequestDto userRequestDto
    ) {
        return ResponseEntity.status(HttpStatus.CREATED.value()).body(
            CommonResponse.<Long>builder()
                .status(HttpStatus.CREATED.value())
                .message("회원가입이 성공하였습니다.")
                .data(userService.createUser(userRequestDto))
                .build()
        );
    }

    @PostMapping("/logout")
    public ResponseEntity<CommonResponse<Long>> logoutUser(
        HttpServletResponse response
    ) {
        userService.logoutUser(response);
        return ResponseEntity.status(HttpStatus.OK.value()).body(CommonResponse.<Long>builder()
            .message("로그아웃 되었습니다.")
            .status(HttpStatus.OK.value())
            .build()
        );
    }
}