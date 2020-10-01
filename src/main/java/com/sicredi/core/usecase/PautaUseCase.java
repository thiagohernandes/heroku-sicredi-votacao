package com.sicredi.core.usecase;

import com.sicredi.core.usecase.http.PautaHttp;
import com.sicredi.dataprovider.PautaDataProvider;
import com.sicredi.dataprovider.entity.Pauta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PautaUseCase {

    private PautaDataProvider pautaDataProvider;

    @Autowired
    public PautaUseCase(final PautaDataProvider pautaDataProvider) {
        this.pautaDataProvider = pautaDataProvider;
    }

    public Pauta cadastrarPauta(PautaHttp pauta) {
        return pautaDataProvider.cadastrarPauta(pauta);
    }

    public Pauta buscarPorId(Long id) {
        return pautaDataProvider.buscarPorId(id);
    }

    public String abrirSessao(Long idPauta, Integer periodoMinutos) {
        return this.pautaDataProvider.abrirSessao(idPauta, periodoMinutos);
    }

}
