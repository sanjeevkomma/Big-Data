package com.demo.service;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@Service
public class HdfsService {

    private final Configuration configuration;

    public HdfsService(Configuration configuration) {
        this.configuration = configuration;
    }

    public String writeToHdfs(String fileName, String content) throws Exception {
        FileSystem fs = FileSystem.get(configuration);
        Path path = new Path("/demo/" + fileName);

        try (FSDataOutputStream outputStream = fs.create(path, true)) {
            outputStream.writeUTF(content);
        }
        return "File written to HDFS: " + path;
    }

    public String readFromHdfs(String fileName) throws Exception {
        FileSystem fs = FileSystem.get(configuration);
        Path path = new Path("/demo/" + fileName);

        try (FSDataInputStream inputStream = fs.open(path);
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            return reader.readLine();
        }
    }
}
