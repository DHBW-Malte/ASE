package com.cor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class CommandRecorder {
    public void save(List<Command> commands, Path filePath) {
        JSONArray jsonArray = new JSONArray();

        for (Command command : commands) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("commandType", command.getClass().getSimpleName());
            jsonObject.put("timestamp", LocalDateTime.now().toString());
            jsonArray.put(jsonObject);
        }

        try {
            Path parent = filePath.getParent();
            if (parent != null) {
                Files.createDirectories(parent);
            }
            Files.writeString(filePath, jsonArray.toString(4));
        } catch (IOException e) {
            System.err.println("Error writing command history: " + e.getMessage());
        }
    }
}
