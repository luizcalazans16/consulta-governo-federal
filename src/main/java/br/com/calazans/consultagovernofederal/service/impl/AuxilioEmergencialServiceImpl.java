package br.com.calazans.consultagovernofederal.service.impl;

import br.com.calazans.consultagovernofederal.SendRequestTask;
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
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Service
@Slf4j
public class AuxilioEmergencialServiceImpl implements AuxilioEmergencialService {

    @Override
    public List<GovernoFederalResponseDto> findAuxilioEmergencialByCpf(String cpf) {
        List<AuxilioEmergencialDto> auxilioEmergencialList = new ArrayList<>();
        List<GovernoFederalResponseDto> governoFederalResponseList = new ArrayList<>();
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder(URI.create(Constants.DEFAULT_URL
                .concat(Constants.AUXILIO_EMERGENCIAL_BY_CPF_ENDPOINT)
                .concat(cpf)))
                .header("chave-api-dados", "69dd055486cdd4ee571e55b6f66afb6f")
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
        System.out.println("Abrindo thread...");
        ExecutorService executorService = Executors.newCachedThreadPool();
        System.out.println("Enviando requisição para o serviço do governo federal...");
        Future<HttpResponse<String>> futureCall = executorService.submit(task);
        HttpResponse<String> result = null;

        try {
            result = futureCall.get();
            System.out.println("Shutting down...");
            executorService.shutdown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return result;
    }

}
