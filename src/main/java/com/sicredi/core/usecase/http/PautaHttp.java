package com.sicredi.core.usecase.http;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PautaHttp {

    @JsonProperty("id")
    private Long id;
    @JsonProperty("descricao")
    private String descricao;

}
