package com.leonde.seconddawn.controller;

import com.leonde.seconddawn.entity.UserRegistration;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.Valid;
@Validated
@RequestMapping("/second-dawn/v1/registration")
@OpenAPIDefinition(info = @Info(title = "Register User"), servers = { @Server(url = "http://localhost:8080", description = "Local Server.")})
public interface RegistrationInterface {
        @Operation(
                summary = "Creates a user",
                description = "User then has an valid account",
                responses = {
                        @ApiResponse(
                                responseCode = "201",
                                description = "Successful Creation method returned",
                                content =  @Content(mediaType = "Application/json",
                                        schema = @Schema(implementation = UserRegistration.class))),
                        @ApiResponse(responseCode = "400",
                                description = "The request parameters are invalid",
                                content = @Content(mediaType = " Application/json")),
                        @ApiResponse(responseCode = "500",
                                description = "An unplanned error occured.",
                                content = @Content(mediaType = " Application/json"))})

        @PostMapping
        @ResponseStatus(code = HttpStatus.CREATED)
        String registerUser(@Valid @RequestBody UserRegistration register);
}




