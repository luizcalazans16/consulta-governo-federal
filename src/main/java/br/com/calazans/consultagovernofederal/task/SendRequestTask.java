package br.com.calazans.consultagovernofederal.task;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.Callable;

public class SendRequestTask implements Callable<HttpResponse<String>> {

    private HttpClient httpClient;

    private HttpRequest httpRequest;

    public SendRequestTask(HttpClient client, HttpRequest request) {
        this.httpClient = client;
        this.httpRequest = request;
    }

    @Override
    public HttpResponse<String> call() throws Exception {
        return httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
    }


}
