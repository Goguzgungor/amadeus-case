package com.example.amadeuscase.flight.service.crud;

import com.example.amadeuscase.flight.entity.FlightEntity;
import com.example.amadeuscase.flight.model.CreateFlightDto;

import java.util.List;

public interface IFlightService {


    void createFlight(CreateFlightDto createFlightDto);


    void deleteFlight(long id);

    List<FlightEntity> getAllFlights();


    void updateFlight(long id, CreateFlightDto createFlightDto);
}
