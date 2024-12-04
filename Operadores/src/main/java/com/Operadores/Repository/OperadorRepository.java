package com.Operadores.Repository;

import com.Operadores.Dto.OperadorDto;
import com.Operadores.Entities.Operador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OperadorRepository extends JpaRepository<Operador,Long> {

    @Query("SELECT o FROM Operador o WHERE idOperador = :idOperador")
    Operador findOperadorById(Long idOperador);

    @Query("SELECT new com.Operadores.Dto.OperadorDto(o.idOperador, o.nombreOperador, o.imgLogo) FROM Operador o")
    List<OperadorDto> findAllOperadores();

    @Query("SELECT new com.Operadores.Dto.OperadorDto(o.idOperador, o.nombreOperador, o.imgLogo) FROM Operador o WHERE idOperador = :idOperador")
    OperadorDto findOperadorDtoById(Long idOperador);

}
