package com.example.amadeuscase.airport.controller;

import com.example.amadeuscase.airport.entity.AirPortEntity;
import com.example.amadeuscase.airport.model.UpdateAirPortDto;
import com.example.amadeuscase.airport.service.IAirPortService;
import com.example.amadeuscase.core.model.ResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/amadeus/airport")
@Tag(name = "Airport", description = "Airport Crud operations for amaadeus case study")
public class AirPortController {


    private final IAirPortService airPortService;

    @Autowired
    public AirPortController(IAirPortService airPortService) {
        this.airPortService = airPortService;
    }


    @Operation(
            summary = "Create an airport",
            description = "Creates a new airport with the provided city."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Airport created successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseDto.class)))
    })
    @GetMapping("/create")
    public ResponseEntity<ResponseDto> create(
            @RequestParam(name = "city", required = true) String city) {
        try {
            airPortService.createAirPort(city);
            return ResponseDto.ok(city + " Airport Created", HttpStatus.CREATED.toString());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDto(e.getMessage(), "400"));
        }
    }



    @Operation(
            summary = "Delete an airport by ID",
            description = "Deletes an airport with the provided ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Airport deleted successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseDto.class)))
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto> delete(
            @PathVariable(name = "id") long id) {
        try {
            airPortService.deleteAirPort(id);
            return ResponseDto.ok("Airport Deleted", HttpStatus.ACCEPTED.toString());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDto(e.getMessage(), "400"));
        }
    }


    @Operation(
            summary = "Update an airport",
            description = "Updates an airport with the provided information."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Airport updated successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseDto.class)))
    })
    @PutMapping("/")
    public ResponseEntity<ResponseDto> update(
            @RequestBody UpdateAirPortDto updateAirPortDto) {
        try {
            airPortService.updateAirPort(updateAirPortDto.getId(), updateAirPortDto.getCity());
            return ResponseDto.ok("Airport Updated", HttpStatus.ACCEPTED.toString());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDto(e.getMessage(), "400"));
        }
    }


    @Operation(
            summary = "Get all airports",
            description = "Retrieves a list of all airports."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful retrieval of airports",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AirPortEntity.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("/all")
    public List<AirPortEntity> getAllAirports() {
        try {
            return airPortService.getAllAirPorts();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

}
