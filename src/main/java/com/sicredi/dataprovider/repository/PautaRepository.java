package com.sicredi.dataprovider.repository;

import com.sicredi.dataprovider.entity.Pauta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PautaRepository extends JpaRepository<Pauta,Long> {
}
