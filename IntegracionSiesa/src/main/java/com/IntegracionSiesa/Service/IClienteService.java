package com.IntegracionSiesa.Service;

import com.IntegracionSiesa.dto.ClienteDto;
import com.IntegracionSiesa.dto.PlantaDto;

import java.util.List;
import java.util.Optional;

public interface IClienteService {

    Optional<List<ClienteDto>> listClientes();

    Optional<List<ClienteDto>> findById(String nit);

    Optional<List<PlantaDto>> findSpecialCustomers();

    Optional<PlantaDto> findEspecialCustomerByIdPlanta(String idPlanta);

    Long findIdClienteByNit(Integer nitCliente);

}
