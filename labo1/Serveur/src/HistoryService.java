import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class HistoryService {
    private static Queue<Message> messages = new LinkedList<>();
    private static final String CHAT_HISTORY_PATH = Utils.HISTORY_PATH;
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    private static final int MAX_HISTORY_SIZE = 15;
    
    public HistoryService() {
        loadMessages();
    }

    private void loadMessages() {
        File file = new File(CHAT_HISTORY_PATH);
        if (!file.exists()) {
            System.out.println("Chat history not found, creating a new one.");
            messages = new LinkedList<>();
            saveMessages();
            return;
        }

        try (Reader reader = new FileReader(file)) {
        	messages = GSON.fromJson(reader, new TypeToken<Queue<Message>>() {}.getType());

            if (messages == null) {
                messages = new LinkedList<>();
            }
        } catch (IOException e) {
            System.err.println("Error loading chat history: " + e.getMessage());
        }
    }

    public void saveMessages() {
        try {
            File file = new File(CHAT_HISTORY_PATH);
            File parentDir = file.getParentFile();
            if (parentDir != null && !parentDir.exists()) {
                parentDir.mkdirs();
            }
            try (Writer writer = new FileWriter(file)) {
                GSON.toJson(messages, writer);
            }
        } catch (IOException e) {
            System.err.println("Error saving chat history: " + e.getMessage());
        }
    }

    public Queue<Message> getHistory() {
        return messages;
    }

    public void addMessage(Message message) {
        messages.add(message);
        if (messages.size() > MAX_HISTORY_SIZE) {
            messages.poll();
        }
        saveMessages();
    }

    public static String getFormattedHistory() {
        StringBuilder historyBuilder = new StringBuilder();
        for (Message message : messages) {
            historyBuilder.append(message.toString()).append("\n");
        }
        return historyBuilder.toString();
    }
}
