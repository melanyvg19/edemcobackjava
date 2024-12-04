package com.Operadores.Service;

import com.Operadores.Dto.TarifaOperadorDto;
import com.Operadores.Entities.Operador;
import com.Operadores.Entities.TarifaOperador;
import com.Operadores.Exceptions.ResourceNotFoundException;
import com.Operadores.Repository.OperadorRepository;
import com.Operadores.Repository.TarifaOperadorRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class TarifaOperadorService {

    @Autowired
    private TarifaOperadorRepository tarifaOperadorRepository;

    @Autowired
    private OperadorRepository operadorRepository;

    Logger LOGGER = Logger.getLogger(TarifaOperadorService.class);

    public List<TarifaOperadorDto> guardarTarifa(List<TarifaOperadorDto> dto) {
        LOGGER.info("Se ha guardado un registro");

        LocalDate currentDate = LocalDate.now();
        int mes = currentDate.getMonthValue() == 1 ? 12 : currentDate.getMonthValue() - 1;
        int anio = currentDate.getMonthValue() == 1 ? currentDate.getYear() - 1 : currentDate.getYear();

        List<TarifaOperadorDto> newTarifaOperador = new ArrayList<>();

        for (TarifaOperadorDto tarifaOperadorDto : dto) {
            Operador operador = operadorRepository.findOperadorById(tarifaOperadorDto.getIdOperador());
            tarifaOperadorRepository.save(TarifaOperador.builder()
                    .idTarifaOperador(tarifaOperadorDto.getIdTarifa())
                    .tarifaOperador(tarifaOperadorDto.getTarifaOperador())
                    .anio(anio)
                    .mes(mes)
                    .operador(operador).build());

            TarifaOperadorDto nuevaTarifaOperadorDto = new TarifaOperadorDto();
            nuevaTarifaOperadorDto.setIdTarifa(tarifaOperadorDto.getIdTarifa());
            nuevaTarifaOperadorDto.setMes(mes);
            nuevaTarifaOperadorDto.setAnio(anio);
            nuevaTarifaOperadorDto.setIdOperador(tarifaOperadorDto.getIdOperador());
            nuevaTarifaOperadorDto.setTarifaOperador(tarifaOperadorDto.getTarifaOperador());

            newTarifaOperador.add(nuevaTarifaOperadorDto);
        }

        return newTarifaOperador;
    }


    public Optional<TarifaOperador> buscarTarifa(Long id) throws ResourceNotFoundException {
        LOGGER.info("Iniciando la busqueda de una tarifa con id: " + id);
        Optional<TarifaOperador> tarifaABuscar = tarifaOperadorRepository.findById(id);
        if (tarifaABuscar.isPresent()) {
            return tarifaABuscar;
        } else {
            throw new ResourceNotFoundException("El registro con id: " + id + " no existe.");
        }
    }

    public List<TarifaOperadorDto> lisTarifaOperador() {
        return tarifaOperadorRepository.findAllPlantas();
    }

    public Optional<List<TarifaOperadorDto>> findLastTarifaOperadores() {
        return Optional.ofNullable(tarifaOperadorRepository.findLastTarifasOperadores());
    }

    public Operador encontrarOperador(TarifaOperadorDto dto) {
        return operadorRepository.findOperadorById(dto.getIdOperador());
    }

    public TarifaOperadorDto findTarifaOperadorByIdOperadorAndMonth(Long idOperador, Integer mes){
        return tarifaOperadorRepository.findTarifaOperadorByIdOperadorAndMonth(idOperador, mes);
    }


}
