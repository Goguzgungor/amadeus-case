package com.example.amadeuscase.flight.controller;

import com.example.amadeuscase.core.model.ResponseDto;
import com.example.amadeuscase.flight.model.CreateFlightDto;
import com.example.amadeuscase.flight.service.crud.IFlightService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/amadeus/flight")
@RestController
@Tag(name = "Flight", description = "Flight Crud operations for amaadeus case study")
public class FlightController {


    private final IFlightService flightService;


    @Autowired
    public FlightController(IFlightService flightService) {
        this.flightService = flightService;
    }


    @Operation(
            summary = "Create a new flight",
            description = "Creates a new flight based on the provided information."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Flight Created",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class)))
    })
    @PostMapping("/")
    public ResponseEntity<ResponseDto> create(
            @Parameter(description = "Flight information to be created")
            @RequestBody CreateFlightDto createFlightDto) {
        try {
            flightService.createFlight(createFlightDto);
            return ResponseEntity.ok(new ResponseDto("Flight Created", HttpStatus.CREATED.toString()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ResponseDto(e.getMessage(), HttpStatus.BAD_REQUEST.toString()));
        }
    }


    @Operation(
            summary = "Delete a flight by ID",
            description = "Deletes a flight based on the specified flight ID."
    )
    @ApiResponse(responseCode = "200", description = "Flight successfully deleted", content = @Content(mediaType = "application/json"))
    @ApiResponse(responseCode = "400", description = "Invalid flight ID provided", content = @Content)
    @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    @DeleteMapping("/{id}")
    public ResponseEntity delete(
            @Parameter(description = "Flight ID to be deleted")
            @PathVariable(name = "id") long id) {
        try {
            flightService.deleteFlight(id);
            return ResponseDto.ok(new ResponseDto("Flight Deleted", HttpStatus.ACCEPTED.toString()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @Operation(
            summary = "Update a flight by ID",
            description = "Updates a flight based on the specified flight ID."
    )
    @ApiResponse(responseCode = "200", description = "Flight successfully updated", content = @Content(mediaType = "application/json"))
    @ApiResponse(responseCode = "400", description = "Invalid flight ID or payload provided", content = @Content)
    @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    @PutMapping("/{id}")
    public ResponseEntity update(
            @Parameter(description = "Flight ID to be updated")
            @PathVariable(name = "id") long id,
            @RequestBody CreateFlightDto createFlightDto) {
        try {
            flightService.updateFlight(id, createFlightDto);
            return ResponseEntity.ok(new ResponseDto("Flight Updated", HttpStatus.ACCEPTED.toString()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Operation(
            summary = "Get all flights",
            description = "Returns a list of all flights."
    )
    @ApiResponse(responseCode = "200", description = "Successful retrieval of flights", content = @Content(mediaType = "application/json"))
    @ApiResponse(responseCode = "400", description = "Error during retrieval", content = @Content)
    @GetMapping("/all")
    public ResponseEntity getAll() {
        try {
            return ResponseEntity.ok(flightService.getAllFlights());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


}
