package com.sicredi.dataprovider.mapper;

import com.sicredi.core.usecase.http.VotoHttp;
import com.sicredi.dataprovider.entity.Voto;

import java.util.ArrayList;
import java.util.List;

public class VotoMapper {

    public static Voto httpToEntity(VotoHttp votoHttp) {
        return Voto.builder()
                    .id(votoHttp.getId())
                    .cpf(votoHttp.getCpf())
                    .resposta(votoHttp.isResposta())
                    .pauta(votoHttp.getPauta())
                    .build();
    }

    public static VotoHttp entityToHttp(Voto voto) {
        return VotoHttp.builder()
                .id(voto.getId())
                .cpf(voto.getCpf())
                .resposta(voto.isResposta())
                .pauta(voto.getPauta())
                .build();
    }

    public static List<VotoHttp> entityToHttp(List<Voto> listaVotos) {
        List<VotoHttp> listaVotoHttp = new ArrayList<>();
        listaVotos.stream().forEach(voto -> {
            listaVotoHttp.add(VotoHttp.builder()
                    .id(voto.getId())
                    .cpf(voto.getCpf())
                    .resposta(voto.isResposta())
                    .pauta(voto.getPauta())
                    .build());
        });
        return listaVotoHttp;
    }

}
