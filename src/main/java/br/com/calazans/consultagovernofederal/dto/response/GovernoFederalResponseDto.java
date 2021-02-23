package br.com.calazans.consultagovernofederal.dto.response;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@JsonDeserialize(builder = GovernoFederalResponseDto.GovernoFederalResponseDtoBuilder.class)
public class GovernoFederalResponseDto {

    private  Integer id;

    private String nome;

    private String cpf;

    private Double valor;

    private String mesCompetencia;

    private String cidade;

    @JsonPOJOBuilder(withPrefix = "")
    public static final class GovernoFederalResponseDtoBuilder {

    }
}
