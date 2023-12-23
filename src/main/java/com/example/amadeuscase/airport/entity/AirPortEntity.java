package com.example.amadeuscase.airport.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "airports")
public class AirPortEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "city")
    private String city;

    public AirPortEntity(String city) {
        this.city = city;
    }

    public AirPortEntity() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    // Diğer Getter ve Setter metotları
}
