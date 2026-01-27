package com.demo.service;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.springframework.stereotype.Service;

@Service
public class SparkJobService {

    private final SparkSession sparkSession;

    public SparkJobService(SparkSession sparkSession) {
        this.sparkSession = sparkSession;
    }

    public long processCsv() {

        Dataset<Row> df = sparkSession.read()
                .option("header", "true")
                .option("inferSchema", "true")
                .csv("src/main/resources/data.csv");

        df.show();

        return df.count();
    }
}
