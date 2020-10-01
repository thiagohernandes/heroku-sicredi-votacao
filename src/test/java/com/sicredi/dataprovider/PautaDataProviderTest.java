package com.sicredi.dataprovider;

import com.sicredi.core.handler.exception.HandlerValidationException;
import com.sicredi.core.usecase.http.PautaHttp;
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
public class PautaDataProviderTest {

    @InjectMocks
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
        Mockito.when(pautaRepository.saveAndFlush(PautaMapper.httpToEntity(pautaHttp)))
                .thenReturn(pauta);
        pautaDataProvider
                .cadastrarPauta(pautaHttp);
    }

    @Test
    public void buscarPautaPorId__Success__Test() throws HandlerValidationException {
        Mockito.when(pautaRepository.findById(id))
                .thenReturn(Optional.of(pauta));
        pautaDataProvider.buscarPorId(id);
    }

    @Test
    public void abrirSessao__Success__Test() throws HandlerValidationException {
        Mockito.when(pautaRepository.verificaExistenciaPauta(11L))
                .thenReturn(1L);
        pautaDataProvider.abrirSessao(id,1);
    }
}
