package com.microservice.generation.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "msvc-facturacion-especial", url = "localhost:9081")
public interface FacturacionEspecialClient {

    @GetMapping("/api/facturacion_especial/cantidadkwh")
    Float findCantidadKWhByIdPlantaAndDate(@RequestParam(name = "idPlanta")String idPlanta,
                                                  @RequestParam(name = "anio")Integer anio,
                                                  @RequestParam(name = "mes")Integer mes) throws Exception;
}
