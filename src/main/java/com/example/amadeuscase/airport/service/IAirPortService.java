package com.example.amadeuscase.airport.service;

import com.example.amadeuscase.airport.entity.AirPortEntity;

import java.util.List;
import java.util.Optional;

public interface IAirPortService {

    void createAirPort(String city);

    Optional<AirPortEntity> getAirPortById(Long id);

    void updateAirPort(Long id, String city);

    void deleteAirPort(Long id);

    List<AirPortEntity> getAllAirPorts();
}
