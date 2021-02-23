package br.com.calazans.consultagovernofederal.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@JsonDeserialize(builder = BeneficiarioDto.BeneficiarioDtoBuilder.class)
public class BeneficiarioDto {

    private String cpfFormatado;
    private Boolean multiploCadastro;
    private String nis;
    private String nome;

    @JsonPOJOBuilder(withPrefix = "")
    public static final class BeneficiarioDtoBuilder {

    }
}
