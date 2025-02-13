import java.util.Date;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;

//import java.io.FileWriter;
//import java.io.IOException;
//import java.io.Writer;
//
//import com.google.gson.Gson;

public class Serveur {
	
	private static ServerSocket Listener;
    public static void main(String[] args) throws Exception {
    	
    	int clientNumber = 0;
    	String serverAddress = "127.0.0.1";
    	int serverPort = 5000;
    	
    	Listener = new ServerSocket();
    	Listener.setReuseAddress(true);
    	
    	InetAddress serverIP = InetAddress.getByName(serverAddress);
    	Listener.bind(new InetSocketAddress(serverIP, serverPort));
    	
    	System.out.format("The server is running on %s:%d%n", serverAddress, serverPort);
    	
    	try {
    		while(true) {
    			new ClientHandler(Listener.accept(), clientNumber++).start();
    		} 
    	} finally {
    			Listener.close();
    	}
    	
    	
//    	System.out.println(ValidationService.isValidIpv4("123.123.12.12"));
//
//        System.out.println(ValidationService.isValidUser("admrin", "pwsdfd"));
//
//        System.out.println(new Message("user1", "123.123.123.123", 50001, new Date(), "hello world"));
//        HistoryService.addMessage("Utilisateur 1", "132.207.29.107", 46202, new Date(), "Salut Utilisateur 2 !");
//        HistoryService.addMessage("Utilisateur 2", "132.207.29.117", 37608, new Date(), "Yo Utilisateur 1 !");
//
//        System.out.println("Message History:");
//        System.out.println(HistoryService.getFormattedHistory());
//    	Person person = new Person("mkyong", 42);
//        Gson gson = new Gson();
//
//        // write to this file
//        try (Writer writer = new FileWriter("person.json")) {
//
//            // Convert the Java object `person` into a JSON data and write to a file
//            gson.toJson(person, writer);
//
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
    }
}

