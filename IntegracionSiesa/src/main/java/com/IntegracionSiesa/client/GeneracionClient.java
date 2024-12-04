package com.IntegracionSiesa.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "msvc-generation", url = "localhost:9092")
public interface GeneracionClient {

    @GetMapping("/api/generacion/generacion_actual")
    Double findGeneracionActualByIdPlantaAndDate(@RequestParam(name = "idPlanta")String idPlanta,
                                                 @RequestParam(name = "anio")Integer anio,
                                                 @RequestParam(name = "mes")Integer mes);

    @GetMapping("/api/generacion/valor_total")
    Double findValorTotalByIdPlantaAndDate(@RequestParam(name = "idPlanta")String idPlanta,
                                                 @RequestParam(name = "anio")Integer anio,
                                                 @RequestParam(name = "mes")Integer mes);
}
