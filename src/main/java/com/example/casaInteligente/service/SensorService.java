package com.example.casaInteligente.service;

import com.example.casaInteligente.model.Sensor;
import com.example.casaInteligente.repository.SensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SensorService {

    @Autowired
    private SensorRepository sensorRepository;

    public List<Sensor> listarTodos() {
        return sensorRepository.findUltimosPorTipo();
    }

    public List<Sensor> salvar(List<Sensor> sensores) {
        return sensorRepository.saveAll(sensores);
    }

}
