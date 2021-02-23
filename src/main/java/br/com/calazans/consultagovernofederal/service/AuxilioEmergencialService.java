package br.com.calazans.consultagovernofederal.service;

import br.com.calazans.consultagovernofederal.dto.response.GovernoFederalResponseDto;

import java.util.List;

public interface AuxilioEmergencialService {

    List<GovernoFederalResponseDto> findAuxilioEmergencialByCpf(final String cpf);
}
