package com.sicredi.core.usecase;

import com.sicredi.core.handler.exception.HandlerValidationException;
import com.sicredi.core.usecase.http.PautaHttp;
import com.sicredi.dataprovider.PautaDataProvider;
import com.sicredi.dataprovider.entity.Pauta;
import com.sicredi.dataprovider.mapper.PautaMapper;
import com.sicredi.dataprovider.repository.PautaRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class PautaUseCaseTest {

    @InjectMocks
    private PautaUseCase pautaUseCase;

    @Mock
    private PautaDataProvider pautaDataProvider;

    private final Long id = 11L;

    private final String descricaoPauta = "Pauta Teste";

    @Mock
    private PautaRepository pautaRepository;

    private final PautaHttp pautaHttp = PautaHttp.builder()
            .descricao(descricaoPauta)
            .build();
    private final Pauta pauta = Pauta.builder()
            .descricao(descricaoPauta)
            .id(11L)
            .build();

    @Test
    public void cadastrarPauta__Success__Test(){
        Mockito.when(pautaDataProvider.cadastrarPauta(pautaHttp))
                .thenReturn(pauta);
        pautaUseCase.cadastrarPauta(pautaHttp);
    }

    @Test
    public void buscarPautaPorId__Success__Test() throws HandlerValidationException {
        Mockito.when(pautaDataProvider.buscarPorId(id)) .thenReturn(pauta);
        pautaUseCase.buscarPorId(id);
    }

    @Test
    public void abrirSessao__Success__Test() throws HandlerValidationException {
        final Integer minutos = 1;
        final Long idPauta = 1L;
        Mockito.when(pautaDataProvider.abrirSessao(idPauta,minutos))
                .thenReturn(String.valueOf(minutos).concat("minuto(s)")
                        .concat(" para votação da pauta: ").concat(String.valueOf(idPauta)));
        pautaUseCase.abrirSessao(idPauta,minutos);
    }
}
