package com.sicredi.dataprovider.fallback.http;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VotoFallbackHttp {

    @JsonProperty("dataHora")
    private LocalDateTime dataHora;
    @JsonProperty("mensagem")
    private String mensagem;

}
