package com.sicredi.dataprovider.mapper;

import com.sicredi.core.usecase.http.PautaHttp;
import com.sicredi.dataprovider.entity.Pauta;

import java.util.ArrayList;
import java.util.List;

public class PautaMapper {

    public static Pauta httpToEntity(PautaHttp pautaHttp) {
        return Pauta.builder()
                    .id(pautaHttp.getId())
                    .descricao(pautaHttp.getDescricao())
                    .build();
    }

    public static PautaHttp entityToHttp(Pauta pauta) {
        return PautaHttp.builder()
                .id(pauta.getId())
                .descricao(pauta.getDescricao())
                .build();
    }

    public static List<PautaHttp> entityToHttp(List<Pauta> listaPautas) {
        List<PautaHttp> listaPautaHttp = new ArrayList<>();
        listaPautas.stream().forEach(pauta -> {
            listaPautaHttp.add(PautaHttp.builder()
                    .id(pauta.getId())
                    .descricao(pauta.getDescricao())
                    .build());
        });
        return listaPautaHttp;
    }

}
