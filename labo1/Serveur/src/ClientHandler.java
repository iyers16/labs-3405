import java.io.IOException;
import java.net.Socket;

public class ClientHandler extends Thread {
	private Socket clientSocket;
	private int clientNumber;
	private AuthService authService;
	
	public ClientHandler(Socket socket, int clientNumber, AuthService authService) {
		this.clientSocket = socket;
		this.clientNumber = clientNumber;
		this.authService = authService;
	}
	
	public void run() {
		if (!this.authService.authenticate(this.clientSocket)) {
			return;
		}
            /*String receivedMessage = in.readUTF();
            System.out.println("Message received from Client#" + clientNumber + " : " + receivedMessage);*/
            
		
		try {
			clientSocket.close();
		} catch (IOException e) {
			System.out.println("Couldn't close the socket, what's going on?");
		}
		System.out.println("Connection with client#" + clientNumber + " closed");
	}
}
