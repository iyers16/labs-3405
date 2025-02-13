import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {
	private static Socket socket;
	
	public static void main(String[] args) throws Exception {
		Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the server's IP address: ");
        //String serverAddress = scanner.nextLine();
        String serverAddress = "127.0.0.1";

        int port = 5000;
        /*
        do {
            System.out.print("Enter server's port (between 5000 et 5050) : ");
            port = scanner.nextInt();
        } while (port < Utils.PORT_MIN || port > Utils.PORT_MAX);
        
        scanner.nextLine();
         */
        try {
            socket = new Socket(serverAddress, port);
            System.out.format("Connected to serveur [%s:%d]%n", serverAddress, port);

            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            
            if (!authenticate(in, out, scanner)) {
                System.out.println("Authentication failed. Closing connection.");
                socket.close();
                return;
            }
			
			System.out.print("✍️  Entrez un message : "); 
			String message = scanner.nextLine(); 
			out.writeUTF(message); 
			out.flush();
			 
            
            System.out.println("📤 Message envoyé au serveur !");
            
            scanner.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	private static boolean authenticate(DataInputStream in, DataOutputStream out, Scanner scanner) throws IOException {
		System.out.print(in.readUTF() + " ");  
	    String username = scanner.nextLine();
	    out.writeUTF(username);

	    System.out.print(in.readUTF() + " ");  
	    String password = scanner.nextLine();
	    out.writeUTF(password);

	    String response = in.readUTF();
	    System.out.println(response);

	    return !response.startsWith("ERROR");
	}
}

