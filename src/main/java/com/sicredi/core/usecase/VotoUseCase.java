package com.sicredi.core.usecase;

import com.sicredi.core.usecase.http.VotoComputadoHttp;
import com.sicredi.core.usecase.http.VotoHttp;
import com.sicredi.dataprovider.VotoDataProvider;
import com.sicredi.dataprovider.entity.Voto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class VotoUseCase {

    private VotoDataProvider votoDataProvider;

    @Autowired
    public VotoUseCase(final VotoDataProvider votoDataProvider) {
        this.votoDataProvider = votoDataProvider;
    }

    public Voto computarVoto(VotoHttp votoHttp) {
        return votoDataProvider.computarVoto(votoHttp);
    }

    public List<VotoComputadoHttp> votosComputadosPorPauta(Long idPauta) {
        return votoDataProvider.votosComputadosPorPauta(idPauta);
    }

    public Long verificaVotoAssociado(Long idPauta, Long cpf) {
        return votoDataProvider.verificaVotoAssociado(idPauta, cpf);
    }




}
