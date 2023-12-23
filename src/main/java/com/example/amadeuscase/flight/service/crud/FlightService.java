package com.example.amadeuscase.flight.service.crud;

import com.example.amadeuscase.flight.entity.FlightEntity;
import com.example.amadeuscase.flight.model.CreateFlightDto;
import com.example.amadeuscase.flight.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class FlightService implements IFlightService{

    private final FlightRepository flightRepository;


    @Autowired
    public FlightService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;

    }


    public void createFlight(CreateFlightDto createFlightDto){
        FlightEntity flightEntity = new FlightEntity(
                createFlightDto.getDepartureAirportId(),
                createFlightDto.getArrivalAirportId(),
                createFlightDto.getDepartureDateTime(),
                createFlightDto.getReturnDateTime(),
                createFlightDto.getPrice()
        );
        try{
        this.flightRepository.save(flightEntity);
        }catch (Exception e){
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public void deleteFlight(long id){
        try{
            this.flightRepository.deleteById(id);
        }catch (Exception e){
            throw new IllegalArgumentException("Flight could not be deleted");
        }
    }

    public void updateFlight(long id, CreateFlightDto createFlightDto){
        FlightEntity flightEntity = new FlightEntity(
                createFlightDto.getDepartureAirportId(),
                createFlightDto.getArrivalAirportId(),
                createFlightDto.getDepartureDateTime(),
                createFlightDto.getReturnDateTime(),
                createFlightDto.getPrice()
        );
        flightEntity.setId(id);
        try{
            this.flightRepository.save(flightEntity);
        }catch (Exception e){
            throw new IllegalArgumentException("Flight could not be updated");
        }
    }

     public List<FlightEntity> getAllFlights(){
        try{
            return this.flightRepository.findAll();
        }catch (Exception e){
            throw new IllegalArgumentException("Flights could not be fetched");
        }
    }
}
