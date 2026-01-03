package com.pragma.powerup.infrastructure.in;

import com.pragma.powerup.application.dto.UserRequest;
import com.pragma.powerup.application.dto.UserResponse;
import com.pragma.powerup.application.handler.UserHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@PreAuthorize("hasRole('ADMIN')")
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserHandler userHandler;

    @Operation(summary = "Add a new user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User created", content = @Content),
            @ApiResponse(responseCode = "409", description = "User already exists", content = @Content)
    })
    @PostMapping("/")
    public ResponseEntity<Void> saveUser(@Valid @RequestBody UserRequest userRequest) {
        userHandler.saveUser(userRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Get all the users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All users returned",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = UserResponse.class)))),
            @ApiResponse(responseCode = "404", description = "No data found", content = @Content)
    })

    @PreAuthorize("hasRole('OWNER')")
    @GetMapping("/")
    public ResponseEntity<List<UserResponse>> getAllUser() {
        String email =
                SecurityContextHolder.getContext()
                        .getAuthentication()
                        .getName();
        System.out.println("CORREO USADO: "+email);
        return ResponseEntity.ok(userHandler.getAllUsers());
    }


    @Operation(summary = "Get a user by their Number")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "user found",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponse.class))),
            @ApiResponse(responseCode = "404", description = "user not found", content = @Content)
    })
    @GetMapping("/{number}")
    public ResponseEntity<UserResponse> getUser(@Parameter(description = "Number of the user to be returned")
                                                                 @PathVariable(name = "number") long userNumber) {
        return ResponseEntity.ok(userHandler.getUser(userNumber));
    }

    @Operation(summary = "Update an existing user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "user updated", content = @Content),
            @ApiResponse(responseCode = "404", description = "user not found", content = @Content)
    })
    @PutMapping("/")
    public ResponseEntity<Void> updateUser(@RequestBody UserRequest userRequest) {
        userHandler.updateUser(userRequest);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Delete a user by their Number")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "user deleted", content = @Content),
            @ApiResponse(responseCode = "404", description = "user not found", content = @Content)
    })
    @DeleteMapping("/{userNumber}")
    public ResponseEntity<Void> deleteUser(@PathVariable long userNumber) {
        userHandler.deleteUser(String.valueOf(userNumber));
        return ResponseEntity.noContent().build();
    }

}
