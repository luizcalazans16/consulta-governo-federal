package br.com.calazans.consultagovernofederal.controller;

import br.com.calazans.consultagovernofederal.dto.response.GovernoFederalResponseDto;
import br.com.calazans.consultagovernofederal.service.AuxilioEmergencialService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/consulta-governo-federal")
@Slf4j
public class AuxilioEmergencialController {

    @Autowired
    private AuxilioEmergencialService auxilioEmergencialService;

    @GetMapping("/{cpf}")
    public List<GovernoFederalResponseDto> searchFederalData(@PathVariable final String cpf) {
        log.info("Buscando auxílio emergencial pelo cpf: [{}]", cpf);
        return auxilioEmergencialService.findAuxilioEmergencialByCpf(cpf);
    }

}
