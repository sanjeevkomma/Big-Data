package com.demo.service;


import jakarta.annotation.PostConstruct;
import org.apache.kudu.ColumnSchema;
import org.apache.kudu.Schema;
import org.apache.kudu.Type;
import org.apache.kudu.client.CreateTableOptions;
import org.apache.kudu.client.Insert;
import org.apache.kudu.client.KuduClient;
import org.apache.kudu.client.KuduScanner;
import org.apache.kudu.client.KuduSession;
import org.apache.kudu.client.KuduTable;
import org.apache.kudu.client.PartialRow;
import org.apache.kudu.client.RowResult;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
@ConditionalOnProperty(
        name = "kudu.enabled",
        havingValue = "true",
        matchIfMissing = true
)
public class KuduService {

    private final KuduClient kuduClient;
    private static final String TABLE_NAME = "users";

    public KuduService(KuduClient kuduClient) {
        this.kuduClient = kuduClient;
       // createTableIfNotExists();
    }

    @PostConstruct
    public void init() {
        createTableIfNotExists();
    }

    private void createTableIfNotExists() {
        try {
            if (kuduClient.tableExists(TABLE_NAME)) return;

            List<ColumnSchema> columns = List.of(
                    new ColumnSchema.ColumnSchemaBuilder("id", Type.INT32)
                            .key(true).build(),
                    new ColumnSchema.ColumnSchemaBuilder("name", Type.STRING).build()
            );

            Schema schema = new Schema(columns);

            CreateTableOptions options = new CreateTableOptions()
                    .addHashPartitions(List.of("id"), 3)
                    .setNumReplicas(1);

            kuduClient.createTable(TABLE_NAME, schema, options);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String insertUser(int id, String name) {
        try {
            KuduTable table = kuduClient.openTable(TABLE_NAME);
            KuduSession session = kuduClient.newSession();

            Insert insert = table.newInsert();
            PartialRow row = insert.getRow();
            row.addInt("id", id);
            row.addString("name", name);

            session.apply(insert);
            session.close();
            return "Inserted successfully";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public List<Map<String, Object>> getUsers() {
        List<Map<String, Object>> users = new ArrayList<>();

        try {
            KuduTable table = kuduClient.openTable(TABLE_NAME);
            KuduScanner scanner = kuduClient.newScannerBuilder(table).build();

            while (scanner.hasMoreRows()) {
                for (RowResult row : scanner.nextRows()) {
                    Map<String, Object> map = new HashMap<>();
                    map.put("id", row.getInt("id"));
                    map.put("name", row.getString("name"));
                    users.add(map);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return users;
    }
}
