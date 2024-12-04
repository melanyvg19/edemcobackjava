package com.microservice.facturacion_especial.persistence;

import com.microservice.facturacion_especial.entities.FacturacionEspecial;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface FacturacionEspecialRepository extends CrudRepository<FacturacionEspecial, Long> {

    @Query("SELECT f.valorExportacion FROM FacturacionEspecial f WHERE f.idPlanta = :idPlanta ORDER BY f.idFacturacionEspecial DESC LIMIT 1")
    Optional<Double> findLastValorExportacionByIdPlanta(String idPlanta);

    @Query("SELECT f.cantidadkWh FROM FacturacionEspecial f WHERE f.idPlanta = :idPlanta AND f.anio = :anio AND f.mes = :mes ORDER BY f.idFacturacionEspecial DESC LIMIT 1")
    Float findCantidadKwhByIdPlantaAndDate(String idPlanta, Integer anio, Integer mes);

}
