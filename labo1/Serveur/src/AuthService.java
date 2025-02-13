import java.io.*;
import java.util.HashMap;
import java.util.Map;


public class AuthService {
	private static final String USER_DB = "users.txt";
	private Map<String, String> users;
	
	public AuthService() {
		this.users = loadUsers();
	}
	
	 public boolean authenticateUser(String username, String password) {
        if (users.containsKey(username)) {
            return users.get(username).equals(password);
        } else {
            users.put(username, password);
            saveUsers();
            return true;
        }
    }
	
	private Map<String, String> loadUsers() {
		Map<String, String> users = new HashMap<>();
		
		try (BufferedReader reader = new BufferedReader(new FileReader(USER_DB))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length == 2) {
                    users.put(parts[0], parts[1]);
                }
            }
		} catch (IOException e) {
			System.out.println("Aucun fichier d'utilisateurs trouvé, création d'un nouveau.");
		}
		return users;
	}
	
	private void saveUsers() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(USER_DB))) {
            for (Map.Entry<String, String> entry : users.entrySet()) {
                writer.write(entry.getKey() + ":" + entry.getValue());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
