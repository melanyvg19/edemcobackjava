package com.IntegracionSiesa.Service;


import com.IntegracionSiesa.dto.PlantaDto;

import java.util.List;
import java.util.Optional;

public interface IPlantaService {

    Optional<List<PlantaDto>> findAllPlantas();

    Double findValorUnidadByIdPlanta(String idPlanta);

    String findNombrePlantaByIdPlanta(String idPlanta);

    String findIdPlantaByNombrePlanta(String nombrePlanta);

    Long findIdOperadorByIdPlanta(String idPlanta);

    List<PlantaDto> modifyPlanta(List<PlantaDto> plantaDtoList);

    String verifyPlantaInFacturacionEspecial(String idPlanta) throws Exception;
}
