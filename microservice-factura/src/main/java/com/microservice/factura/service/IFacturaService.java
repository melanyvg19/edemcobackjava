package com.microservice.factura.service;

import com.microservice.factura.dto.FacturaDTO;
import com.microservice.factura.dto.FacturaRequestDTO;
import com.microservice.factura.entities.Factura;
import com.microservice.factura.exceptions.FacturaNotFoundException;

import java.util.List;
import java.util.Optional;

public interface IFacturaService {

    Optional<List<FacturaDTO>> listFacturaByIdPlanta(String idPlanta) throws FacturaNotFoundException;

    List<FacturaDTO> listFacturaToListFacturaDTO(List<Factura> facturaList);

    Optional<List<FacturaDTO>> findByIdPlantaAndDate(String idPlanta, String date) throws FacturaNotFoundException;

    Optional<List<FacturaDTO>> listFacturaByDate(String date) throws FacturaNotFoundException;

    FacturaRequestDTO addFactura(FacturaRequestDTO facturaRequestDTO) throws Exception;

}
