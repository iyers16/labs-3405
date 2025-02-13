import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.util.Scanner;
import java.io.IOException;

public class Serveur {
	private static ServerSocket Listener;
    private static AuthService authService;
	
    public static void main(String[] args) throws Exception {
    	
    	try (Scanner scanner = new Scanner(System.in)) {
			System.out.print("Entrez l'adresse IP du serveur (ex: 127.0.0.1) : ");
			// String serverAddress = scanner.nextLine();
			String serverAddress = "127.0.0.1";
			int serverPort = 5000;
			/* 
			do {
			    System.out.print("Entrez le port d'écoute (entre 5000 et 5050) : ");
			    serverPort = scanner.nextInt();
			} while (serverPort < Utils.PORT_MIN || serverPort > Utils.PORT_MAX);
			
			scanner.nextLine();
			*/
			
			try {
				Listener = new ServerSocket();
				Listener.setReuseAddress(true);
				InetAddress serverIP = InetAddress.getByName(serverAddress);
				Listener.bind(new InetSocketAddress(serverIP, serverPort));
				
				authService = new AuthService();
				System.out.format("The server is running on %s:%d%n", serverAddress, serverPort);
				
				int clientNum = 0;
				while(true) {
					new ClientHandler(Listener.accept(), clientNum++, authService).start();
				}
			} catch (IOException e) {
			    e.printStackTrace();
			} finally {
				Listener.close();
			}
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

