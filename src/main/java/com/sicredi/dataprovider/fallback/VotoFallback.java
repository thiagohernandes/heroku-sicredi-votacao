package com.sicredi.dataprovider.fallback;

import com.sicredi.dataprovider.fallback.http.VotoFallbackHttp;
import com.sicredi.dataprovider.feign.VotoFeign;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class VotoFallback implements VotoFeign {

    public Object validaCpf(Long cpf) {
        return VotoFallbackHttp.builder()
                .dataHora(LocalDateTime.now())
                .mensagem("Validação de CPF não foi possível! Tente novamente...")
                .build();
    }

}
