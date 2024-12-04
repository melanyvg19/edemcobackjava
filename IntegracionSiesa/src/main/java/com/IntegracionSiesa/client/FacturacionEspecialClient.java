package com.IntegracionSiesa.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "msvc-facturacion-especial", url = "localhost:9081")
public interface FacturacionEspecialClient {

    @GetMapping("/api/facturacion_especial/valor_exportacion")
    ResponseEntity<?> findLastValorExportacionByIdPlanta(@RequestParam(name = "idPlanta") String idPlanta);
}
