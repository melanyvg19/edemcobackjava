package com.IntegracionSiesa.Service;

import com.IntegracionSiesa.Repository.ClienteRepository;
import com.IntegracionSiesa.dto.ClienteDto;
import com.IntegracionSiesa.dto.PlantaDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements IClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public Optional<List<ClienteDto>> listClientes() {
        return Optional.of(clienteRepository.listClientes());
    }

    @Override
    public Optional<List<ClienteDto>> findById(String nit) {
        return Optional.ofNullable(clienteRepository.findByNit(nit));
    }

    @Override
    public Optional<List<PlantaDto>> findSpecialCustomers() {
        return Optional.ofNullable(clienteRepository.findSpecialCustomers());
    }

    @Override
    public Optional<PlantaDto> findEspecialCustomerByIdPlanta(String idPlanta) {
        return Optional.ofNullable(clienteRepository.findEspecialCustomerByIdPlanta(idPlanta));
    }

    @Override
    public Long findIdClienteByNit(Integer nitCliente) {
        return clienteRepository.findIdClienteByNit(nitCliente);
    }

}
