package com.banzato.tarefas_api.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class GeminiService {

    @Value("${gemini.api.key}")
    private String apiKey;

    public String classificarPrioridade(String titulo, String descricao) {
        String url = "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.0-flash:generateContent?key=" + apiKey;

        String prompt = "Classifique a prioridade desta tarefa como ALTA, MEDIA ou BAIXA. "
                + "Responda APENAS com uma dessas três palavras, sem acento, sem explicação, sem ponto final. "
                + "Tarefa: " + titulo + ". Descrição: " + descricao;

        String body = "{\"contents\":[{\"parts\":[{\"text\":\"" + prompt + "\"}]}]}";

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String respBody = response.body();
            System.out.println("Resposta Gemini: " + respBody);

            if (respBody.contains("ALTA")) return "ALTA";
            if (respBody.contains("MEDIA")) return "MEDIA";
            if (respBody.contains("BAIXA")) return "BAIXA";
            return "MEDIA";

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            return "MEDIA";
        }
    }
}