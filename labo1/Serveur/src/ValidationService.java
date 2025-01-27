
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.net.URL;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class ValidationService {
    // User-Pwd pairs loaded in-memory
    private static Map<String, String> users = new HashMap<>();
    // Librairie de serialization de json
    private static final Gson GSON = new Gson();

    // load les informations des utilisateurs des la premier reference statique
    static {
        loadUsers();
    }

    public static boolean isValidIpv4(String ip) {
        return ip.matches(Utils.IPV4_REGEX);
    }

    public static boolean isValidPort(int port) {
        return port >= Utils.PORT_MIN && port <= Utils.PORT_MAX;
    }

    /*
     * Si un username n'est pas en memoire, le creer.
     * sinon si le username existe mais le pwd est errone, erreur
     * sinon si les deux sont existants, true.
     */

    public static boolean isValidUser(String username, String password) {
        if (users.get(username) == null) {
            users.put(username, password);
            saveUsers();
            System.out.println("User created with credentials: " + username + " -> " + password);
            return true;
        } else {
            if (users.get(username).equals(password)) {
                return true;
            } else {
                System.out.println("User entered wrong password!");
                return false;
            }
        }
    }

    // Helper functions for IO with user_data file
    private static void loadUsers() {
        try (FileReader reader = new FileReader(getFileFromResources(Utils.USER_DATA_PATH))) {
            users = GSON.fromJson(reader, new TypeToken<Map<String, String>>() {
            }.getType());
            if (users == null) {
                users = new HashMap<>();
            }
        } catch (IOException e) {
            throw new RuntimeException("Error loading users from file: " + Utils.USER_DATA_PATH, e);
        } catch (Exception e) {
            throw new RuntimeException("Unexpected error occurred while loading users.", e);
        }
    }

    private static void saveUsers() {
        try (FileWriter writer = new FileWriter(getFileFromResources(Utils.USER_DATA_PATH))) {
            GSON.toJson(users, writer);
        } catch (IOException e) {
            throw new RuntimeException("Error saving users to file: " + Utils.USER_DATA_PATH, e);
        } catch (Exception e) {
            throw new RuntimeException("Unexpected error occurred while saving users.", e);
        }
    }

    private static String getFileFromResources(String fileName) {
        try {
            URL resource = ValidationService.class.getClassLoader().getResource(fileName);
            if (resource == null) {
                throw new IllegalArgumentException("File not found in resources: " + fileName);
            }
            return resource.getFile();
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving file from resources: " + fileName, e);
        }
    }
}