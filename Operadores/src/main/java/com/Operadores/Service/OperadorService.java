package com.Operadores.Service;

import com.Operadores.Dto.OperadorDto;
import com.Operadores.Repository.OperadorRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OperadorService {

    @Autowired
    private OperadorRepository operadorRepository;

    Logger LOGGER = Logger.getLogger(OperadorService.class);

    public List<OperadorDto> listarOperadores() throws NullPointerException {
        LOGGER.info("Iniciando el listado de los operadores" );
        return operadorRepository.findAllOperadores();
    }

    public OperadorDto findOperadorDtoById(Long idOperador){
        return operadorRepository.findOperadorDtoById(idOperador);
    }


}
