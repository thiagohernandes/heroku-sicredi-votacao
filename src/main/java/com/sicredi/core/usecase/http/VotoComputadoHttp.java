package com.sicredi.core.usecase.http;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sicredi.dataprovider.entity.Pauta;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VotoComputadoHttp {

    @JsonProperty("pauta")
    private String pauta;
    @JsonProperty("sim")
    private Long sim;
    @JsonProperty("nao")
    private Long nao;

}
