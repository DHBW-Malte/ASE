package com.cor;

public class CommandRecorder {
    public void save(List<Command> commands, String filePath) {
        JSONArray jsonArray = new JSONArray();

        for (Command command : commands) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("commandType", command.getClass().getSimpleName());
            jsonObject.put("timestamp", LocalDateTime.now().toString());
            jsonArray.put(jsonObject);
        }

        try (FileWriter file = new FileWriter(filePath)) {
            file.write(jsonArray.toString(4));
        } catch (IOException e) {
            System.err.println("Error writing command history: " + e.getMessage());
        }
    }
}