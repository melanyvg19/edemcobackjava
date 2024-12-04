package com.IntegracionSiesa.Controller;

import com.IntegracionSiesa.Service.PlantaServiceImpl;
import com.IntegracionSiesa.dto.PlantaDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/planta")
@CrossOrigin(origins = "*", allowedHeaders = {"Authorization", "Content-Type"})
public class PlantaController {

    @Autowired
    private PlantaServiceImpl plantaService;

    @GetMapping("/all")
    public ResponseEntity<?> findAllPlantas(){
        return ResponseEntity.ok(plantaService.findAllPlantas());
    }

    @GetMapping("/idplanta")
    public String findIdPlantaByNombrePlanta(@RequestParam("nombrePlanta") String nombrePlanta){
        return plantaService.findIdPlantaByNombrePlanta(nombrePlanta);
    }

    @GetMapping("/idoperador")
    public Long findIdOperadorByIdPlanta(@RequestParam(name = "idPlanta") String idPlanta){
        return plantaService.findIdOperadorByIdPlanta(idPlanta);
    }

    @GetMapping("/valorUnidad")
    public Double findValorUnidadByIdPlanta(@RequestParam(name = "idPlanta") String idPlanta){
        return plantaService.findValorUnidadByIdPlanta(idPlanta);
    }

    @GetMapping("/checkfacturacionespecial")
    public String checkFacturacionEspecial(@RequestParam(name = "idPlanta") String idPlanta) throws Exception {
        return plantaService.verifyPlantaInFacturacionEspecial(idPlanta);
    }

    @PatchMapping("/updatePlanta")
    public List<PlantaDto> updatePlanta(@RequestBody List<PlantaDto> plantaDtoList){
        return plantaService.modifyPlanta(plantaDtoList);
    }

}
