package com.microservice.facturacion_especial.service;

import com.microservice.facturacion_especial.dto.FacturacionEspecialDTO;
import com.microservice.facturacion_especial.entities.FacturacionEspecial;
import com.microservice.facturacion_especial.exceptions.FacturacionEspecialException;

import java.util.List;

public interface IFacturacionEspecialService {

    List<FacturacionEspecial> findAll();

    FacturacionEspecial findById(Long id);

    Double findLastValorExportacionByIdPlanta(String idPlanta) throws Exception;

    FacturacionEspecial save(FacturacionEspecialDTO facturacionEspecial) throws FacturacionEspecialException;

    Float findCantidadKwhByIdPlantaAndDate(String idPlanta, Integer anio, Integer mes) throws Exception;

}
