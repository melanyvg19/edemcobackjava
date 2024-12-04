package com.IntegracionSiesa.Repository;

import com.IntegracionSiesa.Entities.Planta;
import com.IntegracionSiesa.dto.PlantaDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlantaRepository extends JpaRepository<Planta,String> {

    @Query("SELECT new com.IntegracionSiesa.dto.PlantaDto(p.idPlanta, p.asunto, p.centroCosto, p.nombrePlanta, p.urlImg, p.idOperador, p.cliente.idCliente, p.valorUnidad, p.porcentajeAumento) FROM Planta p")
    List<PlantaDto> findAllPlantas();

    @Query("SELECT p.valorUnidad FROM Planta p WHERE p.idPlanta = :idPlanta")
    Double findValorUnidadByIdPlanta(String idPlanta);

    @Query("SELECT p.nombrePlanta FROM Planta p WHERE p.idPlanta = :idPlanta")
    String findNombrePlantaByIdPlanta(String idPlanta);

    @Query("SELECT p.idPlanta FROM Planta p WHERE p.nombrePlanta = :nombrePlanta")
    String findIdPlantaByNombrePlanta(String nombrePlanta);

    @Query("SELECT p.idOperador FROM Planta p WHERE p.idPlanta = :idPlanta")
    Long findIdOperadorByIdPlanta(String idPlanta);

    @Query("SELECT p.idPlanta FROM Planta p INNER JOIN Cliente c ON p.cliente.idCliente = c.idCliente " +
            "INNER JOIN TipoCliente t ON c.tipoCliente.idTipoCliente = t.idTipoCliente " +
            "WHERE t.idTipoCliente = 2 AND p.idPlanta = :idPlanta")
    String verifyPlantaInFacturacionEspecial(String idPlanta);

}
