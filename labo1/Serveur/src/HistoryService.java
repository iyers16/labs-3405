import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class HistoryService {
    private static final Gson GSON = new Gson();
    private static final int MAX_HISTORY_SIZE = 15;
    private static Queue<Message> messageHistory = new LinkedList<>();

    static {
        loadHistory();
        System.out.println(GSON.toJson(messageHistory));
    }

    // Load message history from file
    private static void loadHistory() {
        try (FileReader reader = new FileReader(Utils.getFileFromResources(Utils.HISTORY_PATH))) {
            // Load history as a list, then transfer it to the queue
            List<Message> loadedHistory = GSON.fromJson(reader, new TypeToken<LinkedList<Message>>() {
            }.getType());
            if (loadedHistory != null) {
                messageHistory.addAll(loadedHistory);
            }
        } catch (FileNotFoundException e) {
            System.out.println("History file not found. Starting with an empty history.");
        } catch (IOException e) {
            throw new RuntimeException("Error loading message history from file: " + Utils.HISTORY_PATH, e);
        }
    }

    // Save message history to file
    private static void saveHistory() {
        try (FileWriter writer = new FileWriter(Utils.getFileFromResources(Utils.HISTORY_PATH))) {
            GSON.toJson(messageHistory, writer);
        } catch (IOException e) {
            throw new RuntimeException("Error saving message history to file: " + Utils.HISTORY_PATH, e);
        }
    }

    // Add a new message to the history
    public static void addMessage(String username, String ip, Integer port, Date timestamp, String messageContent) {
        Message message = new Message(username, ip, port, timestamp, messageContent);

        // Add the new message to the queue
        if (messageHistory.size() >= MAX_HISTORY_SIZE) {
            messageHistory.poll(); // Remove the oldest message
        }
        messageHistory.offer(message);

        // Persist the updated history to the file
        saveHistory();
    }

    // Retrieve all messages in the history
    public static Queue<Message> getHistory() {
        return new LinkedList<>(messageHistory); // Return a copy to avoid modification
    }

    public static String getFormattedHistory() {
        StringBuilder historyBuilder = new StringBuilder();
        for (Message message : messageHistory) {
            historyBuilder.append(message.toString()).append("\n");
        }
        return historyBuilder.toString();
    }
}
