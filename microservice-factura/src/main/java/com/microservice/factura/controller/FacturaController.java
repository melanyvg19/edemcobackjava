package com.microservice.factura.controller;

import com.microservice.factura.dto.FacturaRequestDTO;
import com.microservice.factura.exceptions.FacturaNotFoundException;
import com.microservice.factura.service.IFacturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/historico_facturas")
@CrossOrigin(origins = "*", allowedHeaders = {"Authorization", "Content-Type"})
public class FacturaController {

    @Autowired
    private IFacturaService facturaService;

    @GetMapping("/filter")
    public ResponseEntity<?> findFacturaByNitAndDate(@RequestParam("idPlanta") String idPlanta, @RequestParam(value = "date", required = false) String date) throws FacturaNotFoundException{
        return ResponseEntity.ok(facturaService.findByIdPlantaAndDate(idPlanta, date));
    }

    @GetMapping("/planta")
    public ResponseEntity<?> listFacturaByPlanta(@RequestParam("idPlanta") String idPlanta) throws FacturaNotFoundException{
        return ResponseEntity.ok(facturaService.listFacturaByIdPlanta(idPlanta));
    }

    @GetMapping("/date")
    public ResponseEntity<?> listFacturaByDate(@RequestParam("date") String date) throws FacturaNotFoundException{
        return ResponseEntity.ok(facturaService.listFacturaByDate(date));
    }

    @PostMapping()
    public ResponseEntity<?> addFactura(@RequestBody FacturaRequestDTO facturaRequestDTO) throws Exception {
        System.out.println("Número de factura recibido en el controlador: " + facturaRequestDTO.getNumeroFactura());
        return ResponseEntity.ok(facturaService.addFactura(facturaRequestDTO));
    }

}
