package com.microservice.factura.service;

import com.microservice.factura.dto.FacturaDTO;
import com.microservice.factura.dto.FacturaRequestDTO;
import com.microservice.factura.entities.Factura;
import com.microservice.factura.exceptions.FacturaNotFoundException;
import com.microservice.factura.persistence.FacturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.*;

@Service
public class FacturaServiceImpl implements IFacturaService{

    @Autowired
    private FacturaRepository facturaRepository;

    @Override
    public Optional<List<FacturaDTO>> listFacturaByIdPlanta(String idPlanta) throws FacturaNotFoundException {
        Optional<List<FacturaDTO>> facturaDTOList = Optional.ofNullable(listFacturaToListFacturaDTO(facturaRepository.listfacturaByIdPlanta(idPlanta)));
        if (facturaDTOList.get().isEmpty()){
            throw new FacturaNotFoundException("La planta con el id: " + idPlanta + " no existe");
        }
        return facturaDTOList;
    }

    @Override
    public List<FacturaDTO> listFacturaToListFacturaDTO(List<Factura> facturaList) {
        List<FacturaDTO> facturaDTOList = new ArrayList<>();
        for (Factura factura1: facturaList){
            LocalDate timestamp = factura1.getFechaInicial();
            String year = String.valueOf(timestamp.getYear());
            String month = String.valueOf(timestamp.getMonthValue());
            facturaDTOList.add(FacturaDTO.builder()
                    .mes(month)
                    .anio(year)
                    .pdf(factura1.getPdf())
                    .planta(factura1.getIdPlanta()).build());
        }
        return facturaDTOList;
    }


    @Override
    public Optional<List<FacturaDTO>> findByIdPlantaAndDate(String idPlanta, String date) throws FacturaNotFoundException {
        Optional<List<FacturaDTO>> factura;
        if (date == null){
            factura = Optional.ofNullable(listFacturaToListFacturaDTO(facturaRepository.listfacturaByIdPlanta(idPlanta)));
        }else {
            date = date + "-01";
            LocalDate fecha = LocalDate.parse(date);
            String year = String.valueOf(fecha.getYear());
            String month = String.valueOf(fecha.getMonthValue());
            factura = Optional.ofNullable(listFacturaToListFacturaDTO(facturaRepository.listFacturaByIdPlantaAndDate(idPlanta, year, month)));
        }
        if (factura.get().isEmpty()){
            throw new FacturaNotFoundException("La factura no existe");
        }
        return factura;
    }

    @Override
    public Optional<List<FacturaDTO>> listFacturaByDate(String date) throws FacturaNotFoundException {
        date = date +"-01";
        LocalDate fecha = LocalDate.parse(date);
        String year = String.valueOf(fecha.getYear());
        String month = String.valueOf(fecha.getMonthValue());
        Optional<List<FacturaDTO>> facturaDTOList = Optional.ofNullable(listFacturaToListFacturaDTO(facturaRepository.listFacturaByDate(year,month)));
        if (facturaDTOList.get().isEmpty()){
            throw new FacturaNotFoundException("La fecha no es valida");
        }
        return facturaDTOList;
    }

    @Override
    public FacturaRequestDTO addFactura(FacturaRequestDTO facturaRequestDTO) throws Exception {
        try {
            System.out.println("Número de factura a guardar: " + facturaRequestDTO.getNumeroFactura());

            Factura factura = Factura.builder()
                    .fechaInicial(facturaRequestDTO.getFechaInicial())
                    .fechaFinal(facturaRequestDTO.getFechaFinal())
                    .diasFacturados(facturaRequestDTO.getDiasFacturados())
                    .idCliente(facturaRequestDTO.getIdCliente())
                    .idPlanta(facturaRequestDTO.getIdPlanta())
                    .numeroFactura(facturaRequestDTO.getNumeroFactura())
                    .build();
            facturaRepository.save(factura);
            return facturaRequestDTO;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

}
