package com.leonde.seconddawn.controller;

import com.leonde.seconddawn.entity.DockOrder;
import com.leonde.seconddawn.entity.UpdateDockOrder;
import com.leonde.seconddawn.entity.Weapons;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/second-dawn")
@OpenAPIDefinition(info = @Info(title = "Update Dawn Order"), servers = { @Server(url = "http://localhost:8080", description = "Local Server.")})
public interface UpdateOrder {

    @Operation(summary = "Returns completed message", description = " Returns the message",
            responses = {

                    @ApiResponse(
                            responseCode = "200",
                            description = " The components are returned",
                            content = @Content(mediaType = " Application/json",
                                    schema = @Schema(implementation = UpdateDockOrder.class))),})

    @PutMapping ("/v1/update-order")
    DockOrder updateOrder(@RequestBody UpdateDockOrder updateDockOrder);


    @Operation(summary = "Returns completed message", description = " Returns the message")
    @PutMapping("/v1/update-order/{orderId}/{weapon}")
    Weapons updateWeapon(@PathVariable String weapon, @PathVariable String orderId);


    @Operation(summary = "Returns completed message", description = " Returns the message")
     @DeleteMapping("/v1/{orderId}")
    String deleteOrder(@PathVariable String orderId);
}
