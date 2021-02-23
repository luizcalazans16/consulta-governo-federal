package br.com.calazans.consultagovernofederal.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;

@Value
@Builder
@JsonDeserialize(builder = AuxilioEmergencialDto.AuxilioEmergencialDtoBuilder.class)
public class AuxilioEmergencialDto {

    private Integer id;
    private BeneficiarioDto beneficiario;
    private EnquadramentoAuxilioEmergencialDto enquadramentoDto;
    private String mesDisponibilizacao;
    private MunicipioDto municipioDto;
    private String numeroParcela;
    private ResponsavelAuxilioEmergencialDto responsavelAuxilioEmergencialDto;
    private SituacaoAuxilioEmergencialDto situacaoAuxilioEmergencialDto;
    private Double valor;

    @JsonPOJOBuilder(withPrefix = "")
    public static final class AuxilioEmergencialDtoBuilder {

    }
}
