package com.microservice.generation.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "msvc-integracion", url = "localhost:9090")
public interface IntegracionSiesaClient {

    @GetMapping("/api/planta/idplanta")
    String findIdPlantaByNombrePlanta(@RequestParam("nombrePlanta") String nombrePlanta);

    @GetMapping("/api/planta/idoperador")
    Long findIdOperadorByIdPlanta(@RequestParam(name = "idPlanta") String idPlanta);

    @GetMapping("/api/planta/valorUnidad")
    Double findValorUnidadByIdPlanta(@RequestParam(name = "idPlanta") String idPlanta);

    @GetMapping("/api/planta/checkfacturacionespecial")
    String checkFacturacionEspecial(@RequestParam(name = "idPlanta") String idPlanta) throws Exception;

}

