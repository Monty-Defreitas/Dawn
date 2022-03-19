package com.leonde.seconddawn.controller;


import com.leonde.seconddawn.entity.DockOrder;
import com.leonde.seconddawn.entity.DockOrderRequest;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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
@RequestMapping("/second-dawn/v1/create-order")
@OpenAPIDefinition(info = @Info(title = "Jeep Order Service"), servers = {@Server(url = "http://localhost:8080", description = "Local Server.")})

public interface SaveOrderController {
    @Operation(
            summary = "Create an order for a ship",
            description = " Returns the created ship",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = " The created ship is returned",
                            content = @Content(mediaType = " Application/json",
                                    schema = @Schema(implementation = DockOrder.class))),
                    @ApiResponse(responseCode = "400",
                            description = "The request parameters are invalid",
                            content = @Content(mediaType = " Application/json")),
                    @ApiResponse(responseCode = "404",
                            description = "A ship component was not found",
                            content = @Content(mediaType = " Application/json")),
                    @ApiResponse(responseCode = "500",
                            description = "An unplanned error occured.",
                            content = @Content(mediaType = " Application/json"))})

    @PostMapping("/ship")
    @ResponseStatus(code = HttpStatus.CREATED)
    DockOrder createOrder(@Valid @RequestBody DockOrderRequest orderRequest);
    // @formatter:on
}
