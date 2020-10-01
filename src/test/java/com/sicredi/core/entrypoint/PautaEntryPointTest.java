package com.sicredi.core.entrypoint;

import com.google.gson.Gson;
import com.sicredi.core.usecase.PautaUseCase;
import com.sicredi.core.usecase.http.PautaHttp;
import com.sicredi.dataprovider.entity.Pauta;
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

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class PautaEntryPointTest {

    @Mock
    private PautaUseCase pautaUseCase;
    @InjectMocks
    private PautaEntryPoint pautaEntryPoint;
    private MockMvc mockMvc;
    private String URL_API = "/api/v1/pautas";
    private Gson gson;
    private final String descricaoPauta = "Pauta Teste";

    private final Pauta pauta = Pauta.builder()
            .descricao(descricaoPauta)
            .id(11L)
            .build();

    private final PautaHttp pautaHttp = PautaHttp.builder()
            .descricao(descricaoPauta)
            .id(11L)
            .build();

    @Before
    public void init() {
        gson = new Gson();
        mockMvc = MockMvcBuilders.standaloneSetup(this.pautaEntryPoint).build();
    }

    @Test
    public void cadastrarPauta__Success__Test() throws Exception {
        Mockito.when(this.pautaUseCase.cadastrarPauta(pautaHttp))
                .thenReturn(pauta);
        this.pautaEntryPoint.cadastrarPauta(pautaHttp);

        MvcResult result = mockMvc.perform(post(URL_API)
                .accept(MediaType.APPLICATION_JSON)
                .content(gson.toJson(pautaHttp))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated()).andReturn();
        assertEquals(HttpStatus.CREATED.value(), result.getResponse().getStatus());
    }

    @Test
    public void buscarPautaPorId__Success__Test() throws Exception {
        Mockito.when(this.pautaUseCase.buscarPorId(1L))
                .thenReturn(pauta);
        this.pautaEntryPoint.buscarPorId(1L);

        MvcResult result = mockMvc.perform(get(URL_API.concat("/1"))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
        assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
    }

    @Test
    public void abrirSessao__Success__Test() throws Exception {
        this.pautaEntryPoint.abrirSessao(2L,null);

        MvcResult result = mockMvc.perform(post(URL_API.concat("/abrir-sessao?idPauta=2"))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
        assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
    }

}
