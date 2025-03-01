package com.demo.bigdata.spark;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.SparkConf;
import scala.Tuple2;

import java.util.Arrays;

public class SparkWordCount {
    public static void main(String[] args) {
        // Spark Configuration
        SparkConf conf = new SparkConf().setAppName("SparkWordCount").setMaster("local[*]");
        JavaSparkContext sc = new JavaSparkContext(conf);

        // Read input file
        JavaRDD<String> input = sc.textFile(args[0]);

        // Process data
        JavaPairRDD<String, Integer> wordCounts = input
                .flatMap(line -> Arrays.asList(line.split(" ")).iterator())
                .mapToPair(word -> new Tuple2<>(word, 1))
                .reduceByKey(Integer::sum);

        // Save output
        wordCounts.saveAsTextFile(args[1]);

        sc.close();
    }
}
