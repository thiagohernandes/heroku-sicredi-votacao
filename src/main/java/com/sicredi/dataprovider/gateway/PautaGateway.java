package com.sicredi.dataprovider.gateway;

import com.sicredi.core.handler.exception.HandlerValidationException;
import com.sicredi.core.usecase.http.PautaHttp;
import com.sicredi.dataprovider.entity.Pauta;

public interface PautaGateway {

    Pauta cadastrarPauta(PautaHttp pauta);
    Pauta buscarPorId(Long id) throws HandlerValidationException;
    String abrirSessao(Long idPauta, Integer periodoMinutos) throws HandlerValidationException;

}
