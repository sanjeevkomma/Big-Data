# Swagger UI
* http://localhost:8080/swagger-ui.html
# OpenAPI JSON
* http://localhost:8080/v3/api-docs

# Flow
```arduino
┌─────────────────────┐
│   Spring Boot App   │
│   (REST Controller)│
└─────────┬───────────┘
          │ POST /airflow/trigger?runId=xyz
          ▼
┌─────────────────────┐
│   Airflow REST API  │
│   /api/v1/dags/{dag_id}/dagRuns │
└─────────┬───────────┘
          │ Receives DAG ID = sample_dag
          ▼
┌─────────────────────┐
│ Airflow Scheduler   │
│ Picks up new DAG run│
└─────────┬───────────┘
          │ Executes DAG tasks
          ▼
┌─────────────────────┐
│ sample_dag.py DAG   │
│ PythonOperator tasks│
│   - process_data()  │
│   - other tasks     │
└─────────┬───────────┘
          │ Task outputs / logs
          ▼
┌─────────────────────┐
│ Airflow UI / Logs   │
│ Check DAG run status│
└─────────────────────┘
```
# Flow explanation:
1. Spring Boot exposes a REST endpoint (/airflow/trigger) that your frontend or API client can call.
2. The REST controller sends an HTTP POST to Airflow’s REST API:
```bash
POST http://localhost:8080/api/v1/dags/sample_dag/dagRuns
{ "dag_run_id": "test_run_1" }
```
3. Airflow receives the request and creates a new DAG run for sample_dag.
4. The Airflow Scheduler executes the DAG tasks in order (PythonOperator, BashOperator, etc.).
5. Task logs and results can be viewed in the Airflow UI, and the DAG run status is returned via the REST API to Spring Boot.