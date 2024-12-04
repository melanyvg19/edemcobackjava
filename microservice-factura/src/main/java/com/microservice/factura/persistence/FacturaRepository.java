package com.microservice.factura.persistence;

import com.microservice.factura.entities.Factura;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FacturaRepository extends CrudRepository<Factura, Long> {

    @Query("SELECT f FROM Factura f WHERE f.idPlanta = :idPlanta")
    List<Factura> listfacturaByIdPlanta(String idPlanta);


    @Query("SELECT f FROM Factura f WHERE f.idPlanta = :idPlanta AND EXTRACT(YEAR FROM f.fechaInicial) = :year AND EXTRACT(MONTH FROM f.fechaInicial) = :month")
    List<Factura> listFacturaByIdPlantaAndDate(String idPlanta, String year, String month);

    @Query("SELECT f FROM Factura f WHERE EXTRACT(YEAR FROM f.fechaInicial) = :year AND EXTRACT(MONTH FROM f.fechaInicial) = :month")
    List<Factura> listFacturaByDate(String year, String month);

}
