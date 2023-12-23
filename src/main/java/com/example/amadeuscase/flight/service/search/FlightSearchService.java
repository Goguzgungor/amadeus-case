package com.example.amadeuscase.flight.service.search;

import com.example.amadeuscase.flight.entity.FlightEntity;
import com.example.amadeuscase.flight.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;


@Service
public class FlightSearchService implements IFlightSearchService{


    private final FlightRepository flightRepository;

    @Autowired
    public FlightSearchService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }


    public List<FlightEntity> findAllByArrivalAirportId(int id){
        return this.flightRepository.findAllByArrivalAirportId(id);
    }

    public List<FlightEntity> findAllByDepartureAirportId(int id){
        return this.flightRepository.findAllByDepartureAirportId(id);
    }

    public List<FlightEntity> findAllByDepartureDateTimeBetween(
            Timestamp startTime,
            Timestamp endTime
    ){
        return this.flightRepository.findAllByDepartureDateTimeBetween(startTime, endTime);
    }

    public List<FlightEntity> findAllByDepartureDate(Timestamp searchDate){
        return this.flightRepository.findAllByDepartureDate(searchDate);
    }

    public List<FlightEntity> findAllByReturnDate(Timestamp searchDate){
        return this.flightRepository.findAllByReturnDate(searchDate);
    }

    public List<FlightEntity> findFlightEntitiesByReturnDateTimeAndDepartureDateTime(Timestamp returnDateTime, Timestamp departureDateTime) {
        return this.flightRepository.findFlightEntitiesByReturnDateTimeAndDepartureDateTime(returnDateTime, departureDateTime);
    }


    public List<FlightEntity> findFlightEntitiesByIdOrId(long departureFlightId, long returnFlightId){
        return this.flightRepository.findFlightEntitiesByIdOrId(departureFlightId, returnFlightId);
    }

}
