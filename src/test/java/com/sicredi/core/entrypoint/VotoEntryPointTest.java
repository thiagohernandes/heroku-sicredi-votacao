package com.sicredi.core.entrypoint;

import com.google.gson.Gson;
import com.sicredi.core.usecase.VotoUseCase;
import com.sicredi.core.usecase.http.VotoComputadoHttp;
import com.sicredi.core.usecase.http.VotoHttp;
import com.sicredi.dataprovider.entity.Pauta;
import com.sicredi.dataprovider.entity.Voto;
import com.sicredi.dataprovider.timer.SessaoTimer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class VotoEntryPointTest {

    @Mock
    private VotoUseCase votoUseCase;
    @InjectMocks
    private VotoEntryPoint votoEntryPoint;
    private MockMvc mockMvc;
    private String URL_API = "/api/v1/pautas";
    private Gson gson;
    private final String descricaoPauta = "Pauta Teste";

    private final Long cpf = 123456789L;

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

    @Before
    public void init() {
        gson = new Gson();
        mockMvc = MockMvcBuilders.standaloneSetup(this.votoEntryPoint).build();
    }

    @Test
    public void computarVoto__Success__Test() throws Exception {
        Mockito.when(this.votoUseCase
                .computarVoto(votoHttp))
                .thenReturn(voto);
        SessaoTimer.controleSessao = true;
        Mockito.when(votoUseCase.verificaVotoAssociado(votoHttp.getPauta().getId(), votoHttp.getCpf()))
                .thenReturn(0L);
        this.votoEntryPoint.computarVoto(votoHttp);

        MvcResult result = mockMvc.perform(post(URL_API.concat("/computar-voto"))
                .accept(MediaType.APPLICATION_JSON)
                .content(gson.toJson(votoHttp))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated()).andReturn();
        assertEquals(HttpStatus.CREATED.value(), result.getResponse().getStatus());
    }

    @Test
    public void resultadoVotacaoPauta__Success__Test() throws Exception {
        Mockito.when(this.votoUseCase.votosComputadosPorPauta(3L))
                .thenReturn(Arrays.asList());
        this.votoEntryPoint.resultadoVotacaoPauta(3L);

        MvcResult result = mockMvc.perform(get(URL_API.concat("/3/resultado-votacao"))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
        assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
    }

}
