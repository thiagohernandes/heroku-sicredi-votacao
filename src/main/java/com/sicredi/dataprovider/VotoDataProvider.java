package com.sicredi.dataprovider;

import com.sicredi.core.usecase.http.VotoComputadoHttp;
import com.sicredi.core.usecase.http.VotoHttp;
import com.sicredi.dataprovider.entity.Voto;
import com.sicredi.dataprovider.gateway.VotoGateway;
import com.sicredi.dataprovider.mapper.VotoMapper;
import com.sicredi.dataprovider.repository.VotoRepository;
import com.sicredi.dataprovider.timer.SessaoTimer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class VotoDataProvider implements VotoGateway {

    private final VotoRepository votoRepository;

    @Autowired
    public VotoDataProvider(final VotoRepository votoRepository) {
        this.votoRepository = votoRepository;
    }

    public Voto computarVoto(VotoHttp votoHttp) {
        if (!SessaoTimer.controleSessao) {
            throw new IllegalArgumentException("A sessão já está fechada para a pauta:  ".
                    concat(votoHttp.getPauta().getDescricao()));
        }
        return votoRepository.saveAndFlush(VotoMapper.httpToEntity(votoHttp));
    }

    public List<VotoComputadoHttp> votosComputadosPorPauta(Long idPauta) {
        return votoRepository.votosComputadosPorPauta(idPauta);
    }

    public Long verificaVotoAssociado(Long idPauta, Long cpf) {
        return votoRepository.verificaVotoAssociado(idPauta, cpf);
    }

}
