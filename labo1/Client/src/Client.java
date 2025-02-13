import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {
	private static Socket socket;
	
	public static void main(String[] args) throws Exception {
		
		String serverAddress = "127.0.0.1";
		int port = 5000;
		
		try {
			socket = new Socket(serverAddress, port);
			System.out.format("Server lancé sur [%s: %d]",  serverAddress, port);
			
			DataInputStream in = new DataInputStream(socket.getInputStream());
			
			String helloMessageFromServer = in.readUTF();
			System.out.println(helloMessageFromServer);
			
			DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            Scanner scanner = new Scanner(System.in);
            
            System.out.print("✍️  Entrez un message : ");
            String message = scanner.nextLine();  // Lire l'entrée utilisateur
            out.writeUTF(message);  // Envoyer au serveur
            out.flush();
            
            System.out.println("📤 Message envoyé au serveur !");
            
            scanner.close();
	            
			socket.close();
		} catch (IOException e) {
            e.printStackTrace();
        }
		
		
		
		
	}
}
