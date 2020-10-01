package com.sicredi.core.usecase.http;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sicredi.dataprovider.entity.Pauta;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VotoHttp {

    @JsonProperty("id")
    private Long id;
    @JsonProperty("cpf")
    private Long cpf;
    @JsonProperty("resposta")
    private boolean resposta;
    @JsonProperty("pauta")
    private Pauta pauta;

}
