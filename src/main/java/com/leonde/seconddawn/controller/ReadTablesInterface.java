package com.leonde.seconddawn.controller;

import com.leonde.seconddawn.entity.*;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@RequestMapping("/second-dawn/v1/ship-parts")
@OpenAPIDefinition(info = @Info(title = "Ship components"), servers = {@Server(url = "http://localhost:8080", description = "Local Server.")})
public interface ReadTablesInterface {

    @Operation(summary = "Returns ship components for usage", description = " Returns the components",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = " The created ship is returned",
                            content = @Content(mediaType = " Application/json",
                                    schema = @Schema(implementation = Weapons.class))),
            })
    @GetMapping("/weapons")
    @ResponseStatus(code = HttpStatus.OK)
    List<Weapons> readWeaponGoods();


    @Operation(summary = "Returns shield components for ship constructing", description = " Returns the components",
            responses = {

                    @ApiResponse(
                            responseCode = "200",
                            description = " The components are returned",
                            content = @Content(mediaType = " Application/json",
                                    schema = @Schema(implementation = Shields.class))),
            })
    @GetMapping("/shields")
    @ResponseStatus(code = HttpStatus.OK)
    List<Shields> readShieldGoods();


    @Operation(summary = "Returns ship components for usage", description = " Returns the components",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = " The created ship is returned",
                            content = @Content(mediaType = " Application/json",
                                    schema = @Schema(implementation = Hulls.class)))
            })
    @GetMapping("/hulls")
    @ResponseStatus(code = HttpStatus.OK)
    List<Hulls> readHullGoods();

    @GetMapping("/missiles")
    @ResponseStatus(code = HttpStatus.OK)
    List<Missiles> readMissileGoods();

    @GetMapping("/orders/{orderId}")
    @ResponseStatus(code = HttpStatus.OK)
    DockOrder readDockedOrders(@PathVariable String orderId);
}
