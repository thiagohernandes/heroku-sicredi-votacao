package com.sicredi.dataprovider.repository;

import com.sicredi.core.usecase.http.VotoComputadoHttp;
import com.sicredi.dataprovider.entity.Voto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VotoRepository extends JpaRepository<Voto,Long> {

    @Query(" SELECT new com.sicredi.core.usecase.http.VotoComputadoHttp(v.pauta.descricao, " +
            "      (SELECT COUNT(vv) FROM Voto vv " +
            "      WHERE vv.resposta = true " +
            "      AND vv.pauta.id = :idPauta), " +
            "      (SELECT COUNT(vn) FROM Voto vn " +
            "      WHERE vn.resposta = false" +
            "      AND vn.pauta.id = :idPauta) ) " +
            " FROM Voto v WHERE v.pauta.id = :idPauta " +
            " GROUP BY v.pauta.descricao ")
    List<VotoComputadoHttp> votosComputadosPorPauta(@Param("idPauta") Long idPauta);

    @Query(" SELECT COUNT(v) " +
            " FROM Voto v WHERE v.pauta.id = :idPauta " +
            " AND v.cpf = :cpf ")
    Long verificaVotoAssociado(@Param("idPauta") Long idPauta, @Param("cpf") Long cpf);

}
