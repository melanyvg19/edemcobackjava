package com.microservice.generation.service;

import com.microservice.generation.controller.sto.OperadorDto;
import com.microservice.generation.controller.sto.TarifaOperadorDto;
import com.microservice.generation.dto.DatosGeneracionDTO;
import com.microservice.generation.dto.ValorUnidadDTO;
import com.microservice.generation.dto.GeneratorDTO;
import com.microservice.generation.entities.Generator;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.List;

public interface IGeneratorService {

    Double findGeneracionAcumuladaByDateAndPlanta (Integer anio, Integer mes, String planta);

    Double findAhorroCodosAcumuladoByDateAndPlanta(Integer anio, Integer mes, String planta);

    Double findAhorroAcumuladoByDateAndPlanta(Integer anio, Integer mes, String planta);

    ResponseEntity<?> calculate(List<DatosGeneracionDTO> datosGeneracionDTOList) throws Exception;

    List<Generator> findAll();

    void save(Generator generator);

    ValorUnidadDTO findLastValorUnidad();
    
    List<GeneratorDTO> findGenerationsByDate (Integer anio, Integer mes);

    String findIdPlantaByNombrePlanta(String nombrePlanta);

    Double findGeneracionActualByIdPlantaAndDate(Integer anio, Integer mes, String planta);

    Long findIdOperadorByIdPlanta(String idPlanta);

    OperadorDto getOperadorById(Long idOperador);

    TarifaOperadorDto getTarifaOperadorByOperadorId(Long idOperador, Integer mes);

    Double findValorUnidadByIdPlanta(String idPlanta);

    Double findValorTotalByIdPlantaAndDate(Integer anio, Integer mes, String planta);

    String checkFacturacionEspecial(String idPlanta) throws Exception;

    Float findCantidadKWhByIdPlantaAndDate(String idPlanta, Integer anio, Integer mes) throws Exception;

}
