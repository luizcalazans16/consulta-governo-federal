package br.com.calazans.consultagovernofederal.mapper;

import br.com.calazans.consultagovernofederal.dto.AuxilioEmergencialDto;
import br.com.calazans.consultagovernofederal.dto.response.GovernoFederalResponseDto;
import lombok.experimental.UtilityClass;

@UtilityClass
public class AuxilioEmergencialMapper {

    public static GovernoFederalResponseDto map(AuxilioEmergencialDto auxilioEmergencialDto) {
        return (auxilioEmergencialDto == null) ? null :
                GovernoFederalResponseDto.builder()
                        .id(auxilioEmergencialDto.getId())
                        .nome(auxilioEmergencialDto.getBeneficiario().getNome())
                        .cpf(auxilioEmergencialDto.getBeneficiario().getCpfFormatado())
                        .cidade(auxilioEmergencialDto.getMunicipioDto().getNomeIBGE())
                        .mesCompetencia(auxilioEmergencialDto.getMesDisponibilizacao())
                        .valor(auxilioEmergencialDto.getValor())
                        .build();
    }
}
