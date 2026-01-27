from datetime import datetime
from airflow import DAG
from airflow.operators.python import PythonOperator

def process_data():
    print("Processing data in Airflow DAG...")
    with open("/src/main/resources/input.txt", "r") as f:
        lines = f.readlines()
    word_count = {}
    for line in lines:
        for word in line.strip().split():
            word_count[word] = word_count.get(word, 0) + 1
    print("Word Count:", word_count)

default_args = {
    'owner': 'airflow',
    'start_date': datetime(2026, 1, 1),
}

with DAG('sample_dag', default_args=default_args, schedule_interval=None, catchup=False) as dag:
    task1 = PythonOperator(
        task_id='process_data_task',
        python_callable=process_data
    )
