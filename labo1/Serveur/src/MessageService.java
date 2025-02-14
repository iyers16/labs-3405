import java.io.IOException;
import java.io.DataOutputStream;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class MessageService {
	private final List<DataOutputStream> clients = new CopyOnWriteArrayList<>();
    private final HistoryService historyService;

    public MessageService(HistoryService historyService) {
        this.historyService = historyService;
    }
    
    public void addClient(DataOutputStream out) {
        clients.add(out);
    }

    public void removeClient(DataOutputStream out) {
        clients.remove(out);
    }

    public void sendMessage(String message) {
        historyService.addMessage(message); // Save to history
        broadcast(message);
    }
    
    private void broadcast(String message) {
        for (DataOutputStream out : clients) {
            try {
                out.writeUTF(message);
                out.flush();
            } catch (IOException e) {
                System.err.println("Error sending message: " + e.getMessage());
            }
        }
    }
}
