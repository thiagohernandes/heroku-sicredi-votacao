package com.sicredi.dataprovider.repository;

import com.sicredi.dataprovider.entity.Pauta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PautaRepository extends JpaRepository<Pauta,Long> {

    @Query(" SELECT COUNT(p) " +
            " FROM Pauta p WHERE p.id = :idPauta ")
    Long verificaExistenciaPauta(@Param("idPauta") Long idPauta);
}

