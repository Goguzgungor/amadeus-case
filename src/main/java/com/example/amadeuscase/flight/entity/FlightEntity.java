package com.example.amadeuscase.flight.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "flights")
public class FlightEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @JoinColumn(name = "departure_airport_id")
    private int departureAirportId;



    @JoinColumn(name = "arrival_airport_id")
    private int arrivalAirportId;

    @Column(name = "departure_datetime")
    private Timestamp departureDateTime;

    @Column(name = "return_datetime")
    private Timestamp returnDateTime;

    @Column(name = "price")
    private double price;


    public FlightEntity(int departureAirportId, int arrivalAirportId, Timestamp departureDateTime, Timestamp returnDateTime, double price) {
        this.departureAirportId = departureAirportId;
        this.arrivalAirportId = arrivalAirportId;
        this.departureDateTime = departureDateTime;
        this.returnDateTime = returnDateTime;
        this.price = price;
    }
}
