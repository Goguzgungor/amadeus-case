package com.example.amadeuscase.flight.repository;

import com.example.amadeuscase.flight.entity.FlightEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Timestamp;
import java.util.List;

public interface FlightRepository extends JpaRepository<FlightEntity, Long> {

    List<FlightEntity> findAllByArrivalAirportId(int id);
    List<FlightEntity> findAllByDepartureAirportId(int id);


    @Query("select f from FlightEntity f where f.id = ?1 or f.id = ?2")
    List<FlightEntity> findFlightEntitiesByIdOrId(long departureFlightId, long returnFlightId);

    @Query("SELECT f FROM FlightEntity f WHERE f.departureDateTime BETWEEN :startTime AND :endTime")
    List<FlightEntity> findAllByDepartureDateTimeBetween(
            Timestamp startTime,
            Timestamp endTime
    );

    @Query("SELECT f FROM FlightEntity f WHERE DATE(f.departureDateTime) = DATE(:searchDate) AND f.departureDateTime >= CURRENT_DATE")
    List<FlightEntity> findAllByDepartureDate(Timestamp searchDate);

    @Query("SELECT f FROM FlightEntity f WHERE f.returnDateTime BETWEEN :startTime AND :endTime")
    List<FlightEntity> findAllByReturnDateTimeBetween(
            Timestamp startTime,
            Timestamp endTime
    );

    @Query("SELECT f FROM FlightEntity f WHERE DATE(f.returnDateTime) = DATE(:searchDate) AND f.returnDateTime >= CURRENT_DATE")
    List<FlightEntity> findAllByReturnDate(Timestamp searchDate);

    @Query("select f from FlightEntity f where f.returnDateTime = ?1 and f.departureDateTime = ?2")
    List<FlightEntity> findFlightEntitiesByReturnDateTimeAndDepartureDateTime(
            Timestamp returnDateTime,
            Timestamp departureDateTime
    );


}
