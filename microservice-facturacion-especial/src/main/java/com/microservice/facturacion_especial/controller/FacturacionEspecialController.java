package com.microservice.facturacion_especial.controller;

import com.microservice.facturacion_especial.dto.FacturacionEspecialDTO;
import com.microservice.facturacion_especial.entities.FacturacionEspecial;
import com.microservice.facturacion_especial.exceptions.FacturacionEspecialException;
import com.microservice.facturacion_especial.service.IFacturacionEspecialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/facturacion_especial")
@CrossOrigin(origins = "*", allowedHeaders = {"Authorization", "Content-Type"})
public class FacturacionEspecialController {

    @Autowired
    private IFacturacionEspecialService iFacturacionEspecialService;

    @GetMapping("/all")
    public ResponseEntity<?> listFacturasEspeciales() {
        return ResponseEntity.ok(iFacturacionEspecialService.findAll());
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> createFacturacionEspecial(@RequestBody FacturacionEspecialDTO facturacionEspecialDTO) throws FacturacionEspecialException {
        FacturacionEspecial data = iFacturacionEspecialService.save(facturacionEspecialDTO);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Facturación especial generada correctamente");
        response.put("status", "success");
        response.put("statusCode", 200);
        response.put("data", data);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/valor_exportacion")
    public ResponseEntity<?> findLastValorExportacionByIdPlanta(@RequestParam(name = "idPlanta") String idPlanta){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(iFacturacionEspecialService.findLastValorExportacionByIdPlanta(idPlanta));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/cantidadkwh")
    public Float findCantidadKWhByIdPlantaAndDate(@RequestParam(name = "idPlanta")String idPlanta,
                                                  @RequestParam(name = "anio")Integer anio,
                                                  @RequestParam(name = "mes")Integer mes) throws Exception {
        return iFacturacionEspecialService.findCantidadKwhByIdPlantaAndDate(idPlanta, anio, mes);

    }

}
