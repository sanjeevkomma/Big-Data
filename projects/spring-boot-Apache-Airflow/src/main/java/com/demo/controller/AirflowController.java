package com.demo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import java.util.Map;

@RestController
@RequestMapping("/airflow")
public class AirflowController {

    private final WebClient webClient;

    public AirflowController(@Value("${airflow.base-url}") String baseUrl,
                             @Value("${airflow.username}") String username,
                             @Value("${airflow.password}") String password) {
        this.webClient = WebClient.builder()
                .baseUrl(baseUrl)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeaders(headers -> headers.setBasicAuth(username, password))
                .build();
    }

    @PostMapping("/trigger")
    public Mono<Map> triggerDAG(@RequestParam String dagId,
                                @RequestParam(required = false) String runId) {

        if (runId == null) {
            runId = dagId + "_run_" + System.currentTimeMillis();
        }

        return webClient.post()
                .uri("/dags/{dag_id}/dagRuns", dagId)
                .bodyValue(Map.of("dag_run_id", runId))
                .retrieve()
                .bodyToMono(Map.class);
    }

    @GetMapping("/status")
    public Mono<Map> getDAGRunStatus(@RequestParam String dagId, @RequestParam String dagRunId) {
        return webClient.get()
                .uri("/dags/{dag_id}/dagRuns/{dag_run_id}", dagId, dagRunId)
                .retrieve()
                .bodyToMono(Map.class);
    }
}
