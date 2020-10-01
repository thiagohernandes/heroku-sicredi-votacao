package com.sicredi.core.handler;

import com.sicredi.core.entrypoint.PautaEntryPoint;
import com.sicredi.core.handler.exception.HandlerExceptionNotFound;
import com.sicredi.core.handler.exception.HandlerValidationException;
import com.sicredi.core.usecase.PautaUseCase;
import com.sicredi.core.util.ApiUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@RunWith(MockitoJUnitRunner.class)
public class HandlerGenericHttpRequestTest {

    @Mock
    private PautaUseCase pautaUseCase;

    @Mock
    private ApiUtil apiUtil;

    @InjectMocks
    private PautaEntryPoint pautaEntryPoint;

    @InjectMocks
    private HandlerGenericHttpRequest handlerGenericHttpRequest;

    private MockMvc mockMvc;

    private String URL_API = "/api/v1/pautas";

    @Before
    public void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(pautaEntryPoint)
                .setControllerAdvice(new HandlerGenericHttpRequest(apiUtil))
                .build();
    }

    @Test
    public void handlerResourceNotFound__Error__404() throws Exception {
        MockHttpServletResponse response = mockMvc.perform(get(URL_API.concat("invalid"))
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
        this.handlerGenericHttpRequest.handlerResourceNotFound(Mockito.mock(HandlerExceptionNotFound.class),
                Mockito.mock(HttpServletRequest.class));
    }

    @Test
    public void handleNoHandlerFound__Error__404() throws Exception {
        MockHttpServletResponse response = mockMvc.perform(get(URL_API.concat("not-valid"))
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
        this.handlerGenericHttpRequest.handleNoHandlerFound(Mockito.mock(NoHandlerFoundException.class),
                Mockito.mock(HttpServletRequest.class));
    }

    @Test
    public void handlerResourceValidation__Error__400() throws Exception {
        MockHttpServletResponse response = mockMvc.perform(get(URL_API)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();
        this.handlerGenericHttpRequest.handlerResourceValidation(Mockito.mock(HandlerValidationException.class),
                Mockito.mock(HttpServletRequest.class));
    }

    @Test
    public void handlerException__Error__500() throws Exception {
        MockHttpServletResponse response = mockMvc.perform(get(URL_API)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();
        this.handlerGenericHttpRequest.handlerException(Mockito.mock(Exception.class),
                Mockito.mock(HttpServletRequest.class));
    }

}