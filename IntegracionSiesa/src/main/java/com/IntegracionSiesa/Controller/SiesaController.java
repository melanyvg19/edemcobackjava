package com.IntegracionSiesa.Controller;

import com.IntegracionSiesa.Service.SiesaPruebasService;
import com.IntegracionSiesa.Service.SiesaService;
import com.IntegracionSiesa.dto.IniciarFacturacionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/integracion_siesa")
@CrossOrigin(origins = "*", allowedHeaders = {"Authorization", "Content-Type"})
public class SiesaController {

    @Autowired
    private SiesaService service;

    @Autowired
    private SiesaPruebasService siesaPruebasService;

    @GetMapping("/llenar_datos")
    public ResponseEntity<?> llenarDatos(@RequestBody List<IniciarFacturacionDto> centroOperacionList, @RequestParam("date") LocalDate date){
        return siesaPruebasService.llenarDatos2(centroOperacionList,date);
    }

    @PostMapping("/enviar_factura_siesa")
    public ResponseEntity<?> enviarFacturaSiesa(@RequestBody List<IniciarFacturacionDto> iniciarFacturacionDtoList, @RequestParam("date") LocalDate date){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(siesaPruebasService.envioFacturas(iniciarFacturacionDtoList,date));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

//    @GetMapping("/get1")
//    public ResponseEntity<String> getIntDatosFacturaClienteCen(){
//        return service.getIntDatosFacturaClienteCen();
//    }
//    @GetMapping("/get2/{nit}")
//    public ResponseEntity<String> getIntDatosFacturaDocumentoCen(@RequestParam("nit")String nitCliente){
//        return siesaPruebasService.getIntDatosFacturaDocumentoCen(nitCliente);
//    }
//    @GetMapping("/get3")
//    public ResponseEntity<String> getIntDatosFacturaMvtoCen(){
//        return siesaPruebasService.getIntDatosFacturaMvtoCen();
//    }
//
//    @GetMapping("/get4")
//    public ResponseEntity<String> getInformacionFACen(){
//        return service.getInformacionFACen();
//    }


}
