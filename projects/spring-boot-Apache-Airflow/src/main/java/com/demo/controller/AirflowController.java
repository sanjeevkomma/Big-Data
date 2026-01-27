package com.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/airflow")
public class AirflowController {

    private final String AIRFLOW_TRIGGER_URL = "http://localhost:8080/api/v1/dags/sample_dag/dagRuns";

    @PostMapping("/trigger")
    public ResponseEntity<String> triggerDag(@RequestParam(defaultValue = "manual_run") String runId) {
        RestTemplate restTemplate = new RestTemplate();
        String payload = "{ \"dag_run_id\": \"" + runId + "\" }";

        try {
            ResponseEntity<String> response = restTemplate.postForEntity(AIRFLOW_TRIGGER_URL, payload, String.class);
            return ResponseEntity.ok("DAG triggered successfully: " + response.getBody());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error triggering DAG: " + e.getMessage());
        }
    }
}
