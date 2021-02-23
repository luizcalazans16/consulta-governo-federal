package br.com.calazans.consultagovernofederal.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@JsonDeserialize(builder = ResponsavelAuxilioEmergencialDto.ResponsavelAuxilioEmergencialDtoBuilder.class)
public class ResponsavelAuxilioEmergencialDto {

    private String cpfFormatado;

    private String nis;

    private String nome;

    private String nomeSemAcento;

    @JsonPOJOBuilder(withPrefix = "")
    public static final class ResponsavelAuxilioEmergencialDtoBuilder {

    }
}
