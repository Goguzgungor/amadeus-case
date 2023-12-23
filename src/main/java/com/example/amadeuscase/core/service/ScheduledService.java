package com.example.amadeuscase.core.service;

import com.example.amadeuscase.core.utils.Utils;
import com.example.amadeuscase.flight.entity.FlightEntity;
import com.example.amadeuscase.flight.model.CreateFlightDto;
import com.example.amadeuscase.flight.service.crud.FlightService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@Slf4j
public class ScheduledService {

    private final FlightService flightService;


    public ScheduledService(FlightService flightService) {
        this.flightService = flightService;
    }

    @Scheduled(fixedRate = Utils.ONE_DAY)
    public void addNewFlightCron() {
        log.info("Adding new flights to database");
        RestTemplate restTemplate = new RestTemplate();
        String apiUrl = "https://658702ee468ef171392f2231.mockapi.io/mockApi/flights/flights";
        CreateFlightDto[] flights = restTemplate.getForObject(apiUrl, CreateFlightDto[].class);

        if (flights != null) {
            for (CreateFlightDto flight : flights) {
                flightService.createFlight(flight);
            }
            log.info("New flights added to the database");
        } else {
            log.error("Failed to retrieve flights from the API");
        }
    }
}
