package com.sicredi.dataprovider;

import com.sicredi.core.usecase.http.VotoComputadoHttp;
import com.sicredi.core.usecase.http.VotoHttp;
import com.sicredi.dataprovider.entity.Voto;
import com.sicredi.dataprovider.feign.VotoFeign;
import com.sicredi.dataprovider.gateway.VotoGateway;
import com.sicredi.dataprovider.mapper.VotoMapper;
import com.sicredi.dataprovider.repository.VotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Component
public class VotoDataProvider implements VotoGateway {

    private final VotoRepository votoRepository;
    private final VotoFeign votoFeign;

    @Autowired
    public VotoDataProvider(final VotoRepository votoRepository, final VotoFeign votoFeign) {
        this.votoRepository = votoRepository;
        this.votoFeign = votoFeign;
    }

    public Voto computarVoto(VotoHttp votoHttp) {
        return votoRepository.saveAndFlush(VotoMapper.httpToEntity(votoHttp));
    }

    public List<VotoComputadoHttp> votosComputadosPorPauta(Long idPauta) {
        return votoRepository.votosComputadosPorPauta(idPauta);
    }

    public Long verificaVotoAssociado(Long idPauta, Long cpf) {
        return votoRepository.verificaVotoAssociado(idPauta, cpf);
    }

    public boolean validaCpf(Long cpf) {
        Object obj = votoFeign.validaCpf(cpf);
        String valido = (String)((Map.Entry)((LinkedHashMap)obj).entrySet().toArray()[0]).getValue();
        return valido.equalsIgnoreCase("ABLE_TO_VOTE") == true ? true : false;
    }

}
