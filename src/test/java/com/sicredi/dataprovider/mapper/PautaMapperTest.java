package com.sicredi.dataprovider.mapper;

import com.sicredi.core.usecase.http.PautaHttp;
import com.sicredi.dataprovider.entity.Pauta;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class PautaMapperTest {

    private final String descricaoPauta = "Pauta Teste";

    private final Pauta pauta = Pauta.builder()
            .descricao(descricaoPauta)
            .id(11L)
            .build();

    private final PautaHttp pautaHttp = PautaHttp.builder()
            .descricao(descricaoPauta)
            .id(11L)
            .build();

    @Test
    public void httpToEntity__Success__Test () {
        Assert.assertTrue(PautaMapper.httpToEntity(pautaHttp).equals(pauta));
    }

    @Test
    public void entityToHttp__Success__Test () {
        Assert.assertTrue(PautaMapper.entityToHttp(pauta).equals(pautaHttp));
    }

    @Test
    public void entityToHttpList__Success__Test () {
        List<PautaHttp> listaPautaHttp = Arrays.asList(pautaHttp);
        List<Pauta> listaPauta = Arrays.asList(pauta);
        Assert.assertTrue(PautaMapper.entityToHttp(listaPauta).get(0).equals(listaPautaHttp.get(0)));
    }
}
