package com.example.amadeuscase.airport.repository;

import com.example.amadeuscase.airport.entity.AirPortEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface AirPortRepository extends JpaRepository<AirPortEntity, Long> {

    @Transactional
    @Modifying
    @Query("UPDATE AirPortEntity a SET a.city = :city WHERE a.id = :id")
    void updateCityById(Long id, String city);
}
