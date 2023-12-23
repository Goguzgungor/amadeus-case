package com.example.amadeuscase.flight.controller;

import com.example.amadeuscase.flight.entity.FlightEntity;
import com.example.amadeuscase.flight.service.search.IFlightSearchService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;





@RequestMapping("/amadeus/search/flight")
@RestController
@Tag(name = "Flight Search Controller", description = "Flight search apis for Amadeus Case Study")
public class FlightSearchController {

    private final IFlightSearchService flightSearchService;

    @Autowired
    public FlightSearchController(IFlightSearchService flightSearchService) {
        this.flightSearchService = flightSearchService;
    }


    @GetMapping("/arrival")
    @Operation(
            summary = "Get flights by arrival airport ID",
            description = "Returns a list of flights based on the specified arrival airport ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful retrieval of flights",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Invalid arrival airport ID provided",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)
    })
    public ResponseEntity<List<FlightEntity>> findAllByArrivalAirportId(
            @Parameter(description = "Arrival airport ID")
            @RequestParam int id) {
        try {
            List<FlightEntity> flightEntities = this.flightSearchService.findAllByArrivalAirportId(id);
            return ResponseEntity.ok(flightEntities);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }


    @GetMapping("/departure")
    @Operation(summary = "Get flights by departure airport ID", description = "Returns a list of flights based on the specified departure airport ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful retrieval of flights",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Invalid departure airport ID provided",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)
    })
    public ResponseEntity<List<FlightEntity>> findAllByDepartureAirportId(
            @Parameter(description = "Departure airport ID")
            @RequestParam int id) {
        try {
            List<FlightEntity> flightEntities = this.flightSearchService.findAllByDepartureAirportId(id);
            return ResponseEntity.ok(flightEntities);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }


    @GetMapping("/dateBetween")
    @Operation(summary = "Get flights between two dates", description = "Returns a list of flights based on the specified departure date range.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful retrieval of flights",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Invalid date range provided",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)
    })
    public ResponseEntity<List<FlightEntity>> findAllByDepartureDateTimeBetween(
            @Parameter(description = "Start time of date range in ISO 8601 format", example = "2023-12-21 04:48:06.207000")
            @RequestParam Timestamp departureDateTime,
            @Parameter(description = "End time of date range in ISO 8601 format", example = "2023-12-26 04:48:06.207000")
            @RequestParam Timestamp returnDateTime
    ) {
        try {
            List<FlightEntity> flightEntities = this.flightSearchService.findAllByDepartureDateTimeBetween(departureDateTime, returnDateTime);
            return ResponseEntity.ok(flightEntities);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }


    @GetMapping("/departureDate")
    @Operation(
            summary = "Get flights by departure date",
            description = "Returns a list of flights based on the specified departure date."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful retrieval of flights",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Invalid departure date provided",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)
    })
    public ResponseEntity<List<FlightEntity>> findAllByDepartureDate(
            @Parameter(description = "Departure date in ISO 8601 format", example = "2023-12-23 04:48:06.207000")
            @RequestParam Timestamp searchDate) {
        try {
            List<FlightEntity> flightEntities = this.flightSearchService.findAllByDepartureDate(searchDate);
            return ResponseEntity.ok(flightEntities);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }


    @GetMapping("/2WayFlight")
    @Operation(
            summary = "Get two-way flights by flight IDs",
            description = "Returns a list of flights based on the specified departure and return flight IDs."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful retrieval of flights",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Invalid flight IDs provided",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)
    })
    public ResponseEntity<List<FlightEntity>> find2FlightById(
            @Parameter(description = "Departure flight ID")
            @RequestParam long departureFlightId,
            @Parameter(description = "Return flight ID")
            @RequestParam long returnFlightId) {
        try {
            List<FlightEntity> flightEntities = this.flightSearchService.findFlightEntitiesByIdOrId(departureFlightId, returnFlightId);
            return ResponseEntity.ok(flightEntities);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }



    @GetMapping("/returnDate")
    @Operation(summary = "Get flights by return date", description = "Returns a list of flights based on the specified return date.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful retrieval of flights",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Invalid search date provided",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)
    })
    public ResponseEntity<List<FlightEntity>> findAllByReturnDate(
            @Parameter(description = "Return date in ISO 8601 format", example = "2023-01-01T00:00:00Z")
            @RequestParam("searchDate") Timestamp searchDate) {
        try {
            List<FlightEntity> flightEntities = this.flightSearchService.findAllByReturnDate(searchDate);
            return ResponseEntity.ok(flightEntities);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }


    @GetMapping("/returnDateTimeAndDepartureDateTime")
    @Operation(summary = "Get flights by return date and departure date", description = "Returns a list of flights based on the specified return date and departure date.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful retrieval of flights",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "403", description = "Invalid search date provided",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)
    })
    public ResponseEntity<List<FlightEntity>> findFlightEntitiesByReturnDateTimeAndDepartureDateTime(
            @Parameter(description = "Return date in ISO 8601 format", example = "2023-12-23 04:48:06.207000")
            @RequestParam("returnDateTime") Timestamp returnDateTime,
            @Parameter(description = "Departure date in ISO 8601 format", example = "2023-12-25 04:48:06.207000")
            @RequestParam("departureDateTime") Timestamp departureDateTime) {
        try {
            List<FlightEntity> flightEntities = this.flightSearchService.findFlightEntitiesByReturnDateTimeAndDepartureDateTime(returnDateTime, departureDateTime);
            return ResponseEntity.ok(flightEntities);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }


}
