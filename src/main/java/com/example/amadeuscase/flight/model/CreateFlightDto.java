package com.example.amadeuscase.flight.model;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;



@Getter
@Setter
public class CreateFlightDto {

    private int departureAirportId;
    private int arrivalAirportId;

    private Timestamp departureDateTime;
    private Timestamp returnDateTime;
    private double price;

}
