package com.leonde.seconddawn.controller;


import com.leonde.seconddawn.entity.*;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/dawn/shipparts")
@OpenAPIDefinition(info = @Info(title = "Ship components"), servers = { @Server(url = "http://localhost:8080", description = "Local Server.")})
public interface ReadTablesInterface {
    @Operation(
            summary = "Create an order for a ship",
            description = " Returns the created ship",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = " The created ship is returned",
                            content = @Content(mediaType = " Application/json",
                                    schema = @Schema(implementation = Weapons.class))),
                    @ApiResponse(
                            responseCode = "200",
                            description = " The created ship is returned",
                            content = @Content(mediaType = " Application/json",
                                    schema = @Schema(implementation = Shields.class))),
                    @ApiResponse(
                            responseCode = "200",
                            description = " The created ship is returned",
                            content = @Content(mediaType = " Application/json",
                                    schema = @Schema(implementation = Hulls.class))),
            }
    )

    @GetMapping("/weapons")
    @ResponseStatus(code = HttpStatus.OK)
    List<Weapons> readWeaponGoods();

    @GetMapping("/shields")
    @ResponseStatus(code = HttpStatus.OK)
    List<Shields> readShieldGoods();

    @GetMapping("/hulls")
    @ResponseStatus(code = HttpStatus.OK)
    List<Hulls> readHullGoods();
    // @formatter:on

}
