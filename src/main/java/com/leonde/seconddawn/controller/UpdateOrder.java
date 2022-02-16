package com.leonde.seconddawn.controller;

import com.leonde.seconddawn.entity.DockOrder;
import com.leonde.seconddawn.entity.DockOrderRequest;
import com.leonde.seconddawn.entity.UpdateDockOrder;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/update")
@OpenAPIDefinition(info = @Info(title = "Update Dawn Order"), servers = { @Server(url = "http://localhost:8080", description = "Local Server.")})
public interface UpdateOrder {

    @Operation(summary = "Returns completed message", description = " Returns the message",
            responses = {

                    @ApiResponse(
                            responseCode = "200",
                            description = " The components are returned",
                            content = @Content(mediaType = " Application/json",
                                    schema = @Schema(implementation = UpdateDockOrder.class))),
            } , parameters = {
            @Parameter(name = "OrderKey", allowEmptyValue = false,
                    required = true,
                    description = "The ID for which order you'd like to update"),
    })

//  @PutMapping ("/v1/{someKey}/{rowName}")
//  DockOrder updateOrder(@PathVariable("someKey") String someKey, @PathVariable("rowName") String rowName);

    @PutMapping ("/v1/updateOrder")
    DockOrder updateOrder(@RequestBody UpdateDockOrder updateDockOrder);

    @Operation(summary = "Returns completed message", description = " Returns the message",
            parameters = {
            @Parameter(name = "OrderID", allowEmptyValue = false,
                    required = true,
                    description = "The ID for which order you'd like to delete"),
    })

     @DeleteMapping("/v1/{orderId}")
    String deleteOrder(@PathVariable("orderId") String orderId);
}
