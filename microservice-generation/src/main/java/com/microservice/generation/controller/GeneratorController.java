package com.microservice.generation.controller;

import com.microservice.generation.dto.DatosGeneracionDTO;
import com.microservice.generation.dto.ValorUnidadDTO;
import com.microservice.generation.service.GeneratorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/generacion")
@CrossOrigin(origins = "*", allowedHeaders = {"Authorization", "Content-Type"})
public class GeneratorController {

    @Autowired
    private GeneratorServiceImpl generatorService;

    @PostMapping("/calculos")
    public ResponseEntity<?> calculosGeneracion(@RequestBody List<DatosGeneracionDTO> datosGeneracionDTOList) throws Exception {
        return ResponseEntity.ok(generatorService.calculate(datosGeneracionDTOList));
    }

    @GetMapping("/valor_unidad")
    public ValorUnidadDTO findLastValorUnidad(){
        return generatorService.findLastValorUnidad();
    }

    @GetMapping("/generacion_actual")
    public Double findGeneracionActualByIdPlantaAndDate(@RequestParam(name = "idPlanta")String idPlanta,
                                                        @RequestParam(name = "anio")Integer anio,
                                                        @RequestParam(name = "mes")Integer mes){
        return generatorService.findGeneracionActualByIdPlantaAndDate(anio, mes, idPlanta);
    }

    @GetMapping("/valor_total")
    public Double findValorTotalByIdPlantaAndDate(@RequestParam(name = "idPlanta")String idPlanta,
                                                        @RequestParam(name = "anio")Integer anio,
                                                        @RequestParam(name = "mes")Integer mes){
        return generatorService.findValorTotalByIdPlantaAndDate(anio,mes,idPlanta);
    }
}
