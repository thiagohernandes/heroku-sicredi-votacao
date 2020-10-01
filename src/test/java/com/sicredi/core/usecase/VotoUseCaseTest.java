package com.sicredi.core.usecase;

import com.sicredi.core.usecase.http.VotoComputadoHttp;
import com.sicredi.core.usecase.http.VotoHttp;
import com.sicredi.dataprovider.VotoDataProvider;
import com.sicredi.dataprovider.entity.Pauta;
import com.sicredi.dataprovider.entity.Voto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;

@RunWith(MockitoJUnitRunner.class)
public class VotoUseCaseTest {

    @InjectMocks
    private VotoUseCase votoUseCase;

    @Mock
    private VotoDataProvider votoDataProvider;

    private final Long cpf = 123456789L;
    private final Long idPauta = 2L;
    private final String descricaoPauta = "Pauta Teste";

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

    private VotoComputadoHttp votoComputadoHttp =
            VotoComputadoHttp.builder()
                    .nao(2L)
                    .sim(22L)
                    .pauta(descricaoPauta)
                    .build();

    @Test
    public void computaVoto__Success__Test(){
        Mockito.when(votoDataProvider.computarVoto(votoHttp))
                .thenReturn(voto);
        votoUseCase.computarVoto(votoHttp);
    }

    @Test
    public void votosComputadoPorPauta__Success__Test(){
        Mockito.when(votoDataProvider.votosComputadosPorPauta(idPauta))
                .thenReturn(Arrays.asList(votoComputadoHttp));
        votoUseCase.votosComputadosPorPauta(idPauta);
    }
}
