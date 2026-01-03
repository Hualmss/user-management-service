package com.pragma.powerup.infrastructure.in;

import com.pragma.powerup.application.dto.UserRequest;
import com.pragma.powerup.application.handler.ClientHandler;
import com.pragma.powerup.application.handler.ClientHandlerImpl;
import com.pragma.powerup.application.handler.EmployeeHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/client")
@RequiredArgsConstructor
public class ClientController {

    private final ClientHandler clientHandler;

    @Operation(summary = "Add a new client")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "client created", content = @Content),
            @ApiResponse(responseCode = "409", description = "client already exists", content = @Content)
    })
    @PostMapping("/")
    public ResponseEntity<Void> saveClient(@Valid @RequestBody UserRequest userRequest) {
        clientHandler.saveClient(userRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
