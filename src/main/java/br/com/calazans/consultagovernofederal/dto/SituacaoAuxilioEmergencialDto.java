package br.com.calazans.consultagovernofederal.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@JsonDeserialize(builder = SituacaoAuxilioEmergencialDto.SituacaoAuxilioEmergencialDtoBuilder.class)
public class SituacaoAuxilioEmergencialDto {

    private String descricao;

    @JsonPOJOBuilder(withPrefix = "")
    public static final class SituacaoAuxilioEmergencialDtoBuilder {

    }
}
