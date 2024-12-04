package com.Operadores.Controller;

import com.Operadores.Dto.OperadorDto;
import com.Operadores.Service.OperadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/operador")
@CrossOrigin(origins = "*", allowedHeaders = {"Authorization", "Content-Type"})
public class OperadorController {

    @Autowired
    private OperadorService operadorService;

    @GetMapping
    public ResponseEntity<List<OperadorDto>> listarOperadores(){
        return ResponseEntity.ok(operadorService.listarOperadores());
    }

    @GetMapping("/operadordto")
    public OperadorDto getOperadorById(@RequestParam("idOperador") Long idOperador){
        return operadorService.findOperadorDtoById(idOperador);
    }



}
