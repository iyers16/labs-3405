import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler extends Thread {
	
	private Socket clientSocket;
	private int clientNumber;
	
	public ClientHandler(Socket socket, int clientNumber) {
		this.clientSocket = socket;
		this.clientNumber = clientNumber;
		System.out.println("New location with client#" + clientNumber);
	}
	
	public void run() {
		try {
			DataInputStream in = new DataInputStream(clientSocket.getInputStream());
			DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());
			out.writeUTF("Hello from server - you are client#" + clientNumber);
			
			out.writeUTF("Hello from server - you are client#" + clientNumber);
            out.flush();

            // Recevoir le message du client
            String receivedMessage = in.readUTF();
            System.out.println("📨 Message reçu de Client#" + clientNumber + " : " + receivedMessage);
		} catch (IOException e) {
			System.out.println("Error handling client#" + clientNumber + ":" + e);
		} finally {
			try {
				clientSocket.close();
			} catch (IOException e) {
				System.out.println("Couldn't close the socket, what's going on?");
			}
			System.out.println("Connection with client#" + clientNumber + " closed");
		}
	}
}
