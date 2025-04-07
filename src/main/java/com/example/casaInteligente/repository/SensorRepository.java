package com.example.casaInteligente.repository;

import com.example.casaInteligente.model.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SensorRepository extends JpaRepository<Sensor, Long> {

    @Query("SELECT s FROM Sensor s WHERE s.dataRegistro IN (" +
            "SELECT MAX(s2.dataRegistro) FROM Sensor s2 GROUP BY s2.tipo)")
    List<Sensor> findUltimosPorTipo();
}