package com.Operadores.Repository;

import com.Operadores.Dto.TarifaOperadorDto;
import com.Operadores.Entities.TarifaOperador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TarifaOperadorRepository extends JpaRepository<TarifaOperador,Long> {

    @Query("SELECT new com.Operadores.Dto.TarifaOperadorDto(t.idTarifaOperador, t.tarifaOperador, t.mes, t.anio, t.operador.idOperador) FROM TarifaOperador t")
    List<TarifaOperadorDto> findAllPlantas();

    @Query("SELECT new com.Operadores.Dto.TarifaOperadorDto(t.idTarifaOperador, t.tarifaOperador, t.mes, t.anio, t.operador.idOperador) " +
            "FROM TarifaOperador t WHERE t.idTarifaOperador IN (" +
            "SELECT MAX(t2.idTarifaOperador) FROM TarifaOperador t2 GROUP BY t2.operador.idOperador)")
    List<TarifaOperadorDto> findLastTarifasOperadores();

    @Query("SELECT new com.Operadores.Dto.TarifaOperadorDto(t.idTarifaOperador, t.tarifaOperador, t.mes, t.anio, t.operador.idOperador) FROM TarifaOperador t " +
            "WHERE t.operador.idOperador = :idOperador AND t.mes = :mes ORDER BY t.idTarifaOperador DESC LIMIT 1")
    TarifaOperadorDto findTarifaOperadorByIdOperadorAndMonth(Long idOperador, Integer mes);

}
