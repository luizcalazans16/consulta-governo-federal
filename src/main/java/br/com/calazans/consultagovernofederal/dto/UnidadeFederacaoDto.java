package br.com.calazans.consultagovernofederal.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@JsonDeserialize(builder = UnidadeFederacaoDto.UnidadeFederacaoDtoBuilder.class)
public class UnidadeFederacaoDto {

    private String nome;

    private String sigla;

    @JsonPOJOBuilder(withPrefix = "")
    public static final class UnidadeFederacaoDtoBuilder {

    }
}
