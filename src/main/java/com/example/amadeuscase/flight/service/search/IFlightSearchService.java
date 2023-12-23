package com.example.amadeuscase.flight.service.search;

import com.example.amadeuscase.flight.entity.FlightEntity;

import java.sql.Timestamp;
import java.util.List;

public interface IFlightSearchService {

    List<FlightEntity> findAllByArrivalAirportId(int id);

    List<FlightEntity> findAllByDepartureAirportId(int id);

    List<FlightEntity> findAllByDepartureDateTimeBetween(
            Timestamp startTime,
            Timestamp endTime
    );

    List<FlightEntity> findAllByDepartureDate(Timestamp searchDate);

    List<FlightEntity> findFlightEntitiesByIdOrId(long departureFlightId, long returnFlightId);
    List<FlightEntity> findAllByReturnDate(Timestamp searchDate);

    List<FlightEntity> findFlightEntitiesByReturnDateTimeAndDepartureDateTime(
            Timestamp returnDateTime,
            Timestamp departureDateTime
    );
}
