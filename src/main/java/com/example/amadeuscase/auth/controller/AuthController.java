package com.example.amadeuscase.auth.controller;

import com.example.amadeuscase.auth.entity.AmdUser;
import com.example.amadeuscase.core.jwt.util.JwtUtil;
import com.example.amadeuscase.auth.model.request.UserCreateDto;
import com.example.amadeuscase.auth.model.request.UserLoginDto;
import com.example.amadeuscase.auth.model.response.ErrorRes;
import com.example.amadeuscase.auth.model.response.LoginRes;
import com.example.amadeuscase.auth.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@Slf4j
@RequestMapping("/amadeus/auth")
@Tag(name = "Authentication", description = "Authentication and authorization operations for amaadeus case study")
public class AuthController {



    private JwtUtil jwtUtil;
    private final UserService userService;

    @Autowired
    public AuthController( JwtUtil jwtUtil, UserService userService) {
        this.jwtUtil = jwtUtil;
        this.userService = userService;
    }

    @Operation(
            summary = "User login",
            description = "Logs in a user with the provided credentials."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Login successful",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = LoginRes.class))),
            @ApiResponse(responseCode = "400", description = "Invalid username or password",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorRes.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorRes.class)))
    })
    @PostMapping("/login")
    public ResponseEntity<?> login(
            @Valid @RequestBody UserLoginDto loginReq) {
        try {
            AmdUser user = userService.login(loginReq);

            if (user != null) {
                String token = jwtUtil.createToken(user.getEmail(), user.getPassword(), user.getRole());
                LoginRes loginRes = new LoginRes(user.getEmail(), token);
                return ResponseEntity.ok(loginRes);
            } else {
                ErrorRes errorResponse = new ErrorRes(HttpStatus.BAD_REQUEST, "Invalid username or password");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
            }

        } catch (BadCredentialsException e) {
            ErrorRes errorResponse = new ErrorRes(HttpStatus.BAD_REQUEST, "Invalid username or password");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        } catch (Exception e) {
            ErrorRes errorResponse = new ErrorRes(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }



    @Operation(
            summary = "Register a new user",
            description = "Registers a new user with the provided information."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User successfully created"),
            @ApiResponse(responseCode = "403", description = "Invalid username or password",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorRes.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorRes.class)))
    })
    @PostMapping("/register")
    public ResponseEntity register(
            @Valid @RequestBody UserCreateDto userCreateDto) {
        try {
            userService.register(userCreateDto);
            return ResponseEntity.ok("User created");
        } catch (BadCredentialsException e) {
            ErrorRes errorResponse = new ErrorRes(HttpStatus.BAD_REQUEST, "Invalid username or password");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        } catch (Exception e) {
            ErrorRes errorResponse = new ErrorRes(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }



}