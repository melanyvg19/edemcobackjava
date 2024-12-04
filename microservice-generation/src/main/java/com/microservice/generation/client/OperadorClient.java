package com.microservice.generation.client;

import com.microservice.generation.controller.sto.OperadorDto;
import com.microservice.generation.controller.sto.TarifaOperadorDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "msvc-operadores", url = "localhost:9091")
public interface OperadorClient {

    @GetMapping("/api/operador/operadordto")
    OperadorDto getOperadorById(@RequestParam("idOperador") Long idOperador);

    @GetMapping("/api/tarifaoperador/tarifaoperadordto")
    TarifaOperadorDto findTarifaOperadorByIdOperadorAndMonth(@RequestParam(name = "idOperador")Long idOperador, @RequestParam(name = "mes") Integer mes);

}
