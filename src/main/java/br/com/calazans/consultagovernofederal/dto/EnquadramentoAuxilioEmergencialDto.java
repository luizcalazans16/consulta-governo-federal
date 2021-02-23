package br.com.calazans.consultagovernofederal.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@JsonDeserialize(builder = EnquadramentoAuxilioEmergencialDto.EnquadramentoAuxilioEmergencialDtoBuilder.class)
public class EnquadramentoAuxilioEmergencialDto {

    private String descricao;


    @JsonPOJOBuilder(withPrefix = "")
    public static final class EnquadramentoAuxilioEmergencialDtoBuilder {

    }
}
