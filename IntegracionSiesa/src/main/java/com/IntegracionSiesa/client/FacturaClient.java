package com.IntegracionSiesa.client;

import com.IntegracionSiesa.dto.FacturaRequestDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "msvc-factura", url = "localhost:8060")
public interface FacturaClient {

    @PostMapping("/api/historico_facturas")
    ResponseEntity<?> addFactura(@RequestBody FacturaRequestDTO facturaRequestDTO);
}
