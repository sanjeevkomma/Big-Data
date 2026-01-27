# Swagger UI
* http://localhost:8081/swagger-ui.html
# OpenAPI JSON
* http://localhost:8081/v3/api-docs

# Flow
```arduino
       +--------------------+
       |  User / Client     |
       +---------+----------+
                 |
                 | 1) Calls Spring Boot REST API (/trigger-dag)
                 v
       +--------------------+
       | Spring Boot App    |
       |  (REST Controller) |
       +---------+----------+
                 |
                 | 2) Prepares request with parameters (optional)
                 |
                 | 3) Calls Airflow REST API
                 |    POST /api/v1/dags/sample_dag/dagRuns
                 v
       +--------------------+
       | Apache Airflow     |
       |  (REST API +       |
       |   Scheduler)       |
       +---------+----------+
                 |
                 | 4) Scheduler picks DAG: sample_dag.py
                 |
                 | 5) Executes DAG Tasks sequentially/parallel
                 v
       +--------------------+
       | DAG Tasks           |
       | (PythonOperator,    |
       |  BashOperator, etc.)|
       +---------+----------+
                 |
                 | 6) DAG execution completes
                 v
       +--------------------+
       | DAG Run Response    |
       | (Success / Fail,    |
       |  Run ID, Logs)      |
       +---------+----------+
                 |
                 | 7) Spring Boot receives response
                 v
       +--------------------+
       | REST API Response   |
       | Returned to User    |
       +--------------------+
```
# Flow explanation:
1.User Call: Client (Postman, browser, frontend) hits Spring Boot endpoint /trigger-dag.
2. Prepare Request: Spring Boot reads input, prepares JSON payload for Airflow.
3. Trigger Airflow: Spring Boot sends POST request to Airflow REST API.
4. DAG Identification: Airflow Scheduler identifies sample_dag.py from DAG folder.
5. Execute Tasks: Airflow executes all tasks inside DAG (can be Python, Bash, or MapReduce logic).
6. Return DAG Run Info: Airflow returns run ID, status, logs.
7. Response to User: Spring Boot sends success/failure info back to client.