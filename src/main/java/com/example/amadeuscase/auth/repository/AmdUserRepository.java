package com.example.amadeuscase.auth.repository;

import com.example.amadeuscase.auth.entity.AmdUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AmdUserRepository extends JpaRepository<AmdUser, Long> {
    AmdUser findByEmail(String email);

}
