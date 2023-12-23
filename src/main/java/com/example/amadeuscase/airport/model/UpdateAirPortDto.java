package com.example.amadeuscase.airport.model;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateAirPortDto {
    private String city;

    private long id;

    public UpdateAirPortDto(String city, long id) {
        this.city = city;
        this.id = id;
    }

}
