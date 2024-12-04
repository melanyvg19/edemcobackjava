package com.IntegracionSiesa.Repository;

import com.IntegracionSiesa.Entities.Cliente;
import com.IntegracionSiesa.dto.ClienteDto;
import com.IntegracionSiesa.dto.PlantaDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    @Query("SELECT new com.IntegracionSiesa.dto.ClienteDto(c.idCliente, c.nombreCliente, c.contrato," +
            "c.nit, c.tipoCliente.idTipoCliente) " +
            " FROM Cliente c")
    List<ClienteDto> listClientes();

    @Query("SELECT new com.IntegracionSiesa.dto.ClienteDto(c.idCliente, c.nombreCliente, c.contrato, " +
            "c.nit, c.tipoCliente.idTipoCliente)" +
            " FROM Cliente c WHERE c.idCliente = :nit")
    List<ClienteDto> findByNit(String nit);

    @Query("SELECT new com.IntegracionSiesa.dto.PlantaDto(p.idPlanta, p.asunto, p.centroCosto, p.nombrePlanta, p.urlImg, p.idOperador, p.cliente.idCliente, p.valorUnidad, p.porcentajeAumento)" +
            " FROM Planta p JOIN Cliente c ON p.cliente.idCliente = c.idCliente" +
            " JOIN TipoCliente t ON c.tipoCliente.idTipoCliente = t.idTipoCliente" +
            " WHERE t.idTipoCliente = 2")
    List<PlantaDto> findSpecialCustomers();

    @Query("SELECT new com.IntegracionSiesa.dto.PlantaDto(p.idPlanta, p.asunto, p.centroCosto, p.nombrePlanta, p.urlImg, p.idOperador, p.cliente.idCliente, p.valorUnidad, p.porcentajeAumento)" +
            " FROM Planta p JOIN Cliente c ON p.cliente.idCliente = c.idCliente" +
            " JOIN TipoCliente t ON c.tipoCliente.idTipoCliente = t.idTipoCliente" +
            " WHERE p.idPlanta = :idPlanta AND t.idTipoCliente = 2")
    PlantaDto findEspecialCustomerByIdPlanta(String idPlanta);

    @Query("SELECT c.idCliente FROM Cliente c WHERE c.nit = :nitCliente")
    Long findIdClienteByNit(Integer nitCliente);

}
