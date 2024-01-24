package com.example.amadeuscase.core.runner;

import com.example.amadeuscase.airport.service.IAirPortService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Component
public class AmadeusAppRunner implements CommandLineRunner{


    private final IAirPortService airPortService;

    @Autowired
    public AmadeusAppRunner(IAirPortService airPortService) {
        this.airPortService = airPortService;
    }

    @Override
    public void run(String... args) throws Exception {
        List<String> airPortList = Arrays.asList("IST", "SAW", "ESB", "ADB", "AYT", "DLM", "GZT", "VAS", "ADA", "KFS", "KYA", "MLX", "MZH", "GZP", "SSX", "TZX", "USQ", "ONQ", "KCO", "MQM", "AJI", "HTY", "KSY", "NOP", "SXZ", "VAN", "BAL", "ERC", "DIY", "ERZ", "TZX", "EZS", "IGD", "KCM", "KSY", "KFS", "KCO", "KFS", "KZR", "MLX", "MZH", "MSR", "SXZ", "NOP", "ONQ", "SZF", "NOP", "NOP");
        for (String airPort : airPortList) {
            airPortService.createAirPort(airPort);
        }
    }
}
