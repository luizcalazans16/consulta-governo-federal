package br.com.calazans.consultagovernofederal.service.impl;

import br.com.calazans.consultagovernofederal.task.SendRequestTask;
import br.com.calazans.consultagovernofederal.constants.Constants;
import br.com.calazans.consultagovernofederal.dto.AuxilioEmergencialDto;
import br.com.calazans.consultagovernofederal.dto.BeneficiarioDto;
import br.com.calazans.consultagovernofederal.dto.MunicipioDto;
import br.com.calazans.consultagovernofederal.dto.response.GovernoFederalResponseDto;
import br.com.calazans.consultagovernofederal.mapper.AuxilioEmergencialMapper;
import br.com.calazans.consultagovernofederal.service.AuxilioEmergencialService;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

@Service
@Slf4j
public class AuxilioEmergencialServiceImpl implements AuxilioEmergencialService {

    private static final String REQUEST_HEADER_NAME_GOVERNO_FEDERAL = "chave-api-dados";
    private static final String REQUEST_HEADER_VALUE_GOVERNO_FEDERAL = "69dd055486cdd4ee571e55b6f66afb6f";

    @Override
    public List<GovernoFederalResponseDto> findAuxilioEmergencialByCpf(String cpf) {
        List<AuxilioEmergencialDto> auxilioEmergencialList = new ArrayList<>();
        List<GovernoFederalResponseDto> governoFederalResponseList = new ArrayList<>();
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder(URI.create(Constants.DEFAULT_URL
                .concat(Constants.AUXILIO_EMERGENCIAL_BY_CPF_ENDPOINT)
                .concat(cpf)))
                .header(REQUEST_HEADER_NAME_GOVERNO_FEDERAL, REQUEST_HEADER_VALUE_GOVERNO_FEDERAL)
                .GET()
                .build();

        HttpResponse<String> httpResponse = invokeCallable(new SendRequestTask(client, request));
        JSONArray jsonArray = new JSONArray(httpResponse.body());

        JSONObject jsonObject;
        for (int i = 0; jsonArray.length() > i; i++) {
            jsonObject = jsonArray.getJSONObject(i);
            auxilioEmergencialList.add(AuxilioEmergencialDto.builder()
                    .id(jsonObject.getInt("id"))
                    .beneficiario(BeneficiarioDto.builder()
                            .cpfFormatado(jsonObject.getJSONObject("beneficiario").getString("cpfFormatado"))
                            .nome(jsonObject.getJSONObject("beneficiario").getString("nome"))
                            .build())
                    .municipioDto(MunicipioDto.builder()
                            .nomeIBGE(jsonObject.getJSONObject("municipio").getString("nomeIBGE"))
                            .build())
                    .mesDisponibilizacao(jsonObject.getString("mesDisponibilizacao"))
                    .valor(jsonObject.getDouble("valor"))
                    .build());
        }
        for (AuxilioEmergencialDto auxilioEmergencialDto : auxilioEmergencialList) {
            GovernoFederalResponseDto map = AuxilioEmergencialMapper.map(auxilioEmergencialDto);
            governoFederalResponseList.add(map);
        }
        log.info("Registros de auxílio emergencial encontrados: [{}]", governoFederalResponseList);
        return governoFederalResponseList;
    }

    private HttpResponse<String> invokeCallable(SendRequestTask task) {
        log.info("Abrindo thread...");
        ExecutorService executorService = Executors.newCachedThreadPool();
        log.info("Thread pool criado: [{}]", executorService.toString());
        log.info("Enviando requisição para o serviço do governo federal...");
        Future<HttpResponse<String>> futureCall = executorService.submit(task);
        log.info("Thread pool após envio: [{}]  ", executorService.toString());
        HttpResponse<String> result = null;

        try {
            result = futureCall.get();
            log.info("Shutting down...");
            executorService.shutdown();
            log.info("Thread pool encerrado: [{}]", executorService.toString());
        } catch (InterruptedException e) {
            log.error(e.getMessage());
            e.printStackTrace();
        } catch (ExecutionException e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

}
