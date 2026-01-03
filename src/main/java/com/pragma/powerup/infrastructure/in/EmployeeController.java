package com.pragma.powerup.infrastructure.in;

import com.pragma.powerup.application.dto.UserRequest;
import com.pragma.powerup.application.dto.UserResponse;
import com.pragma.powerup.application.handler.EmployeeHandler;
import com.pragma.powerup.application.handler.UserHandler;
import io.swagger.v3.oas.annotations.Operation;
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

@PreAuthorize("hasRole('OWNER')")
@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeHandler employeeHandler;

    @Operation(summary = "Add a new employee")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "employee created", content = @Content),
            @ApiResponse(responseCode = "409", description = "employee already exists", content = @Content)
    })
    @PostMapping("/")
    public ResponseEntity<Void> saveEmployee(@Valid @RequestBody UserRequest userRequest) {
        String userId =
                SecurityContextHolder.getContext()
                        .getAuthentication()
                        .getName();
        employeeHandler.saveEmployee(userRequest, Long.parseLong(userId));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Get all the employees")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All employees returned",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = UserResponse.class)))),
            @ApiResponse(responseCode = "404", description = "No data found", content = @Content)
    })
    @GetMapping("/")
    public ResponseEntity<List<UserResponse>> getAllEmployees() {
        String userId =
                SecurityContextHolder.getContext()
                        .getAuthentication()
                        .getName();
        return ResponseEntity.ok(employeeHandler.getAllEmployees(Long.parseLong(userId)));
    }

}
