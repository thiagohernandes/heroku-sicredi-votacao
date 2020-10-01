package com.sicredi.dataprovider.mapper;

import com.sicredi.core.usecase.http.VotoHttp;
import com.sicredi.dataprovider.entity.Pauta;
import com.sicredi.dataprovider.entity.Voto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class VotoMapperTest {

    private final Long cpf = 123456789L;
    private final String descricaoPauta = "Pauta";

    private final Pauta pauta = Pauta.builder()
            .descricao(descricaoPauta)
            .id(11L)
            .build();

    private final Voto voto = Voto.builder()
            .cpf(cpf)
            .pauta(pauta)
            .resposta(true)
            .id(2L)
            .pauta(pauta)
            .build();

    private final VotoHttp votoHttp = VotoHttp.builder()
            .cpf(cpf)
            .pauta(pauta)
            .resposta(true)
            .id(2L)
            .pauta(pauta)
            .build();

    @Test
    public void httpToEntity__Success__Test () {
        Assert.assertTrue(VotoMapper.httpToEntity(votoHttp).equals(voto));
    }

    @Test
    public void entityToHttp__Success__Test () {
        Assert.assertTrue(VotoMapper.entityToHttp(voto).equals(votoHttp));
    }

    @Test
    public void entityToHttpList__Success__Test () {
        List<VotoHttp> listaVotoHttp = Arrays.asList(votoHttp);
        List<Voto> listaVoto = Arrays.asList(voto);
        Assert.assertTrue(VotoMapper.entityToHttp(listaVoto).get(0).equals(listaVotoHttp.get(0)));
    }
}
