from airflow import DAG
from airflow.operators.python_operator import PythonOperator
from datetime import datetime

def process_data():
    print("Processing data in Airflow DAG!")
    # Here you can add MapReduce logic or call Hadoop/Spark jobs

with DAG(
    dag_id="sample_dag",
    start_date=datetime(2026, 1, 1),
    schedule_interval=None,
    catchup=False
) as dag:

    task1 = PythonOperator(
        task_id="process_data_task",
        python_callable=process_data
    )
