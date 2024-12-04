package com.microservice.generation.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.microservice.generation.dto.GeneratorDTO;
import com.microservice.generation.dto.ValorUnidadDTO;
import com.microservice.generation.entities.Generator;

@Repository
public interface GeneratorRepository extends CrudRepository <Generator, Long> {

    @Query ("SELECT g.generacionAcumulado FROM Generator g WHERE g.anio = :anio AND g.mes = :mes AND g.idPlanta = :planta")
    Double findGeneracionAcumuladaByDateAndPlanta (Integer anio, Integer mes, String planta);

    @Query ("SELECT g.ahorroCodosAcumulado FROM Generator g WHERE g.anio = :anio AND g.mes = :mes AND g.idPlanta = :planta ")
    Double findAhorroCodosAcumuladoByDateAndPlanta(Integer anio, Integer mes, String planta);

    @Query ("SELECT g.ahorroAcumulado FROM Generator g WHERE g.anio = :anio AND g.mes = :mes AND g.idPlanta = :planta")
    Double findAhorroAcumuladoByDateAndPlanta(Integer anio, Integer mes, String planta);

    @Query ("SELECT new com.microservice.generation.dto.ValorUnidadDTO (g.anio, g.mes, g.valorUnidad) FROM Generator g ORDER BY g.idGeneracion DESC LIMIT 1")
    ValorUnidadDTO findLastValorUnidad();

    @Query ("SELECT new com.microservice.generation.dto.GeneratorDTO(g.idGeneracion, g.generacionActual, g.generacionAcumulado, g.valorUnidad, " +
            "g.valorTotal, g.diferenciaTarifa, g.ahorroActual, g.ahorroAcumulado, g.ahorroCodosActual, g.ahorroCodosAcumulado, g.anio, g.mes, " +
            "g.idTarifaOperador, g.idPlanta) FROM Generator g WHERE g.mes = :mes AND g.anio = :anio AND g.idPlanta = :planta")
    List<GeneratorDTO> findGenerationsByDate (Integer anio, Integer mes, String planta);

    @Query ("SELECT g.generacionActual FROM Generator g WHERE g.anio = :anio AND g.mes = :mes AND g.idPlanta = :planta")
    Double findGeneracionActualByIdPlantaAndDate(Integer anio, Integer mes, String planta);

    @Query("SELECT g.valorTotal FROM Generator g WHERE g.anio = :anio AND g.mes = :mes AND g.idPlanta = :planta")
    Double findValorTotalByIdPlantaAndDate(Integer anio, Integer mes, String planta);


}
