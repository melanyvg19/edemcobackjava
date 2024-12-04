package com.Operadores.Controller;

import com.Operadores.Dto.TarifaOperadorDto;
import com.Operadores.Entities.Operador;
import com.Operadores.Entities.TarifaOperador;
import com.Operadores.Exceptions.ResourceNotFoundException;
import com.Operadores.Service.TarifaOperadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tarifaoperador")
@CrossOrigin(origins = "*", allowedHeaders = {"Authorization", "Content-Type"})
public class TarifaOperadorController {

    @Autowired
    private TarifaOperadorService tarifaOperadorService;

    @GetMapping("/{id}")
    public ResponseEntity<TarifaOperador> buscarTarifa(@PathVariable Long id) throws ResourceNotFoundException {
        return ResponseEntity.ok(tarifaOperadorService.buscarTarifa(id).orElseThrow(() -> new ResourceNotFoundException("Tarifa no encontrada para este id :: " + id)));
    }

    @PostMapping
    public List<TarifaOperadorDto> registrarTarifa(@RequestBody List<TarifaOperadorDto> tarifaOperadorList) {
        return tarifaOperadorService.guardarTarifa(tarifaOperadorList);
    }

    @GetMapping("/all")
    public ResponseEntity<?> listTarifaOperador() {
        return ResponseEntity.ok(tarifaOperadorService.lisTarifaOperador());
    }

    @GetMapping("/operador")
    public Operador encontrarOperador(@RequestBody TarifaOperadorDto tarifaOperadorDto) {
        return tarifaOperadorService.encontrarOperador(tarifaOperadorDto);
    }

    @GetMapping("/last_tarifas")
    public ResponseEntity<?> findLastTarifaOperadores() {
        return ResponseEntity.ok(tarifaOperadorService.findLastTarifaOperadores());
    }

    @GetMapping("/tarifaoperadordto")
    public TarifaOperadorDto findTarifaOperadorByIdOperadorAndMonth(@RequestParam(name = "idOperador")Long idOperador,
                                                                    @RequestParam(name = "mes") Integer mes){
        return tarifaOperadorService.findTarifaOperadorByIdOperadorAndMonth(idOperador, mes);
    }

}
