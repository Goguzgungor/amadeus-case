package com.example.amadeuscase.airport.service;

import com.example.amadeuscase.airport.entity.AirPortEntity;
import com.example.amadeuscase.airport.repository.AirPortRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class AirPortService implements IAirPortService{


    private final AirPortRepository airPortRepository;

    @Autowired
    public AirPortService(AirPortRepository airPortRepository) {
        this.airPortRepository = airPortRepository;
    }

    @Override
    public void createAirPort(String city) {
        this.airPortRepository.save(new AirPortEntity(city));
    }

    @Override
    public Optional<AirPortEntity> getAirPortById(Long id) {
        return this.airPortRepository.findById(id);
    }

    @Override
    public void updateAirPort(Long id, String city) {
        this.airPortRepository.updateCityById(id, city);
    }

    @Override
    public void deleteAirPort(Long id) {
        this.airPortRepository.deleteById(id);
    }

    @Override
    public List<AirPortEntity> getAllAirPorts() {
        return this.airPortRepository.findAll();
    }
}
