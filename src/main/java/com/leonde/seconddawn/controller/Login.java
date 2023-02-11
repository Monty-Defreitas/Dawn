package com.leonde.seconddawn.controller;

import com.leonde.seconddawn.entity.Empires;
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
@RequestMapping("/second-dawn/v1/login")
@OpenAPIDefinition(info = @Info(title = "Login User"), servers = { @Server(url = "http://localhost:8080", description = "Local Server.")})
public interface Login {
    @Operation(
            summary = "Logs in a created user",
            description = "User can then make orders",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Login message returned",
                            content =  @Content(mediaType = "Application/json",
                                    schema = @Schema(implementation = Empires.class))),
                    @ApiResponse(responseCode = "400",
                            description = "The request parameters are invalid",
                            content = @Content(mediaType = " Application/json")),
                    @ApiResponse(responseCode = "500",
                            description = "An unplanned error occured.",
                            content = @Content(mediaType = " Application/json"))})

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    void loginUser(@Valid @RequestBody Empires login);
}


