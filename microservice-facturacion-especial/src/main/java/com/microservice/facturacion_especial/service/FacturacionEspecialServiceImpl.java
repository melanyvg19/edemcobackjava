package com.microservice.facturacion_especial.service;

import com.microservice.facturacion_especial.dto.FacturacionEspecialDTO;
import com.microservice.facturacion_especial.entities.FacturacionEspecial;
import com.microservice.facturacion_especial.exceptions.FacturacionEspecialException;
import com.microservice.facturacion_especial.persistence.FacturacionEspecialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class FacturacionEspecialServiceImpl implements IFacturacionEspecialService {

    @Autowired
    private FacturacionEspecialRepository facturacionEspecialRepository;

    @Override
    public List<FacturacionEspecial> findAll() {
        return (List<FacturacionEspecial>) facturacionEspecialRepository.findAll();
    }

    @Override
    public FacturacionEspecial findById(Long id) {
        return facturacionEspecialRepository.findById(id).orElseThrow();
    }

    @Override
    public Double findLastValorExportacionByIdPlanta(String idPlanta) throws Exception{
        try {
            Optional<Double> valorExportacionOptional = facturacionEspecialRepository.findLastValorExportacionByIdPlanta(idPlanta);
            if (valorExportacionOptional.isPresent()){
                return valorExportacionOptional.get();
            }else {
                throw new Exception("Valor exportacion no encontrado");
            }
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    public FacturacionEspecial save(FacturacionEspecialDTO facturacionEspecialDTO) throws FacturacionEspecialException {
        if (facturacionEspecialDTO == null) {
            throw new FacturacionEspecialException("El DTO no puede ser nulo");
        }

        float cantidadkWh = facturacionEspecialDTO.getCantidadkWh();
        float excedente = facturacionEspecialDTO.getExcedente();
        float costoAgregado = facturacionEspecialDTO.getCostoAgregado();
        String idPlanta = facturacionEspecialDTO.getIdPlanta();

        if (excedente <= 0 || costoAgregado <= 0) {
            throw new FacturacionEspecialException("Los valores no pueden ser negativos");
        }

        if (costoAgregado >= excedente) {
            throw new FacturacionEspecialException("El Costo Agregado debe ser menor o igual que el Excedente");
        }

        LocalDate currentDate = LocalDate.now();
        Integer mes = currentDate.getMonthValue() == 1 ? 12 : currentDate.getMonthValue() - 1;
        Integer anio = currentDate.getMonthValue() == 1 ? currentDate.getYear() - 1 : currentDate.getYear();

        float valorExportacion = formula(excedente, costoAgregado);

        FacturacionEspecial facturacionEspecial = FacturacionEspecial.builder()
                .cantidadkWh(cantidadkWh)
                .excedente(excedente)
                .costoAgregado(costoAgregado)
                .idPlanta(idPlanta)
                .valorExportacion(valorExportacion)
                .mes(mes)
                .anio(anio)
                .build();

        facturacionEspecialRepository.save(facturacionEspecial);

        return facturacionEspecial;
    }

    @Override
    public Float findCantidadKwhByIdPlantaAndDate(String idPlanta, Integer anio, Integer mes) throws Exception {
        try {
            return facturacionEspecialRepository.findCantidadKwhByIdPlantaAndDate(idPlanta, anio, mes);
        }catch (Exception e){
            throw new FacturacionEspecialException(e.getMessage());
        }
    }

    private float formula(float excedente, float costoAgregado) {
        return excedente - costoAgregado;
    }

}
