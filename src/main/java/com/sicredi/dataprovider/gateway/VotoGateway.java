package com.sicredi.dataprovider.gateway;

import com.sicredi.core.usecase.http.VotoComputadoHttp;
import com.sicredi.core.usecase.http.VotoHttp;
import com.sicredi.dataprovider.entity.Pauta;
import com.sicredi.dataprovider.entity.Voto;

import java.util.List;

public interface VotoGateway {

    Voto computarVoto(VotoHttp votoHttp);
    List<VotoComputadoHttp> votosComputadosPorPauta(Long idPauta);
    Long verificaVotoAssociado(Long idPauta, Long cpf);
    boolean validaCpf(Long cpf);

}
