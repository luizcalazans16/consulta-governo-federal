package br.com.calazans.consultagovernofederal.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@JsonDeserialize(builder = MunicipioDto.MunicipioDtoBuilder.class)
public class MunicipioDto {

    private String codigoIBGE;

    private String codigoRegiao;

    private String nomeIBGE;

    private String nomeIBGEsemAcento;

    private String nomeRegiao;

    private String pais;

    private UnidadeFederacaoDto uf;


    @JsonPOJOBuilder(withPrefix = "")
    public static final class MunicipioDtoBuilder {

    }
}
