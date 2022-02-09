package com.leonde.seconddawn.controller;

import com.leonde.seconddawn.entity.Empires;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@RequestMapping("/dawn")
@OpenAPIDefinition(info = @Info(title = "Create Dawn User"), servers = { @Server(url = "http://localhost:8080", description = "Local Server.")})
public interface CreateEmpireController {
    // @formatter:off
    @Operation(
            summary = "Create an empire for Dawn Universe",
            description = " Returns the created empire",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = " The created Empire is returned",
                            content = @Content(mediaType = " Application/json",
                                    schema = @Schema(implementation = Empires.class))),
                    @ApiResponse(responseCode = "400",
                            description = "The request parameters are invalid",
                            content = @Content(mediaType = " Application/json")),
                    @ApiResponse(responseCode = "500",
                            description = "An unplanned error occured.",
                            content = @Content(mediaType = " Application/json"))},
            parameters = {
                    @Parameter(name = "CreateUser", allowEmptyValue = false,
                            required = true,
                            description = "The order as JSON")})


    @PostMapping("/empire")
    @ResponseStatus(code = HttpStatus.CREATED)
    Empires registerEmpire(@RequestBody Empires createEmpire);

//    @PostMapping("/shiporder")
//    @ResponseStatus(HttpStatus.CREATED)



}
