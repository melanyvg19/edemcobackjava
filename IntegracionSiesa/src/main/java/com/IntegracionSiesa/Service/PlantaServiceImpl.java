package com.IntegracionSiesa.Service;

import com.IntegracionSiesa.Entities.Planta;
import com.IntegracionSiesa.Repository.PlantaRepository;
import com.IntegracionSiesa.dto.PlantaDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PlantaServiceImpl implements IPlantaService{

    @Autowired
    private PlantaRepository plantaRepository;

    @Override
    public Optional<List<PlantaDto>> findAllPlantas() {
        return Optional.ofNullable(plantaRepository.findAllPlantas());
    }

    @Override
    public Double findValorUnidadByIdPlanta(String idPlanta) {
        return plantaRepository.findValorUnidadByIdPlanta(idPlanta);
    }

    @Override
    public String findNombrePlantaByIdPlanta(String idPlanta) {
        return plantaRepository.findNombrePlantaByIdPlanta(idPlanta);
    }

    @Override
    public String findIdPlantaByNombrePlanta(String nombrePlanta) {
        return plantaRepository.findIdPlantaByNombrePlanta(nombrePlanta);
    }

    @Override
    public Long findIdOperadorByIdPlanta(String idPlanta) {
        return plantaRepository.findIdOperadorByIdPlanta(idPlanta);
    }

    @Override
    public List<PlantaDto> modifyPlanta(List<PlantaDto> plantaDtoList) {

        List<PlantaDto> plantaDtolist1 = new ArrayList<>();
        for (PlantaDto plantaDto: plantaDtoList){
            Optional<Planta> planta = plantaRepository.findById(plantaDto.getIdPlanta());
            if (planta.isPresent()){
                if (plantaDto.getUrlImg() != null){
                    planta.get().setUrlImg(plantaDto.getUrlImg());

                }
                if (plantaDto.getAsunto() != null) {
                    planta.get().setAsunto(plantaDto.getAsunto());

                }
                if (plantaDto.getPorcentajeAumento() != null) {
                    planta.get().setPorcentajeAumento(plantaDto.getPorcentajeAumento());
                    Double incremento = planta.get().getValorUnidad() + plantaDto.getPorcentajeAumento() * planta.get().getValorUnidad();
                    planta.get().setValorUnidad(incremento);
                }
                plantaRepository.save(planta.get());
                plantaDtolist1.add(PlantaDto.builder()
                        .idPlanta(planta.get().getIdPlanta())
                        .asunto(planta.get().getAsunto())
                        .centroCosto(planta.get().getCentroCosto())
                        .nombrePlanta(planta.get().getNombrePlanta())
                        .urlImg(planta.get().getUrlImg())
                        .idOperador(planta.get().getIdOperador())
                        .idCliente(planta.get().getCliente().getIdCliente())
                        .valorUnidad(planta.get().getValorUnidad())
                        .porcentajeAumento(planta.get().getPorcentajeAumento())
                        .build());
            }

        }
        return plantaDtolist1;
    }

    @Override
    public String verifyPlantaInFacturacionEspecial(String idPlanta) throws Exception {
        try {
            return plantaRepository.verifyPlantaInFacturacionEspecial(idPlanta);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
}
