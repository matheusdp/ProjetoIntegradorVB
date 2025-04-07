package com.example.casaInteligente.controller;

import com.example.casaInteligente.model.Sensor;
import com.example.casaInteligente.service.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sensores")
public class SensorController {

    @Autowired
    private SensorService sensorService;

    @GetMapping
    public List<Sensor> listarSensores() {
        return sensorService.listarTodos();
    }

    @PostMapping
    public List<Sensor> adicionarSensor(@RequestBody List<Sensor> sensores) {
        return sensorService.salvar(sensores);
    }

}
