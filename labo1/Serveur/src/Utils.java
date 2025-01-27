import java.net.URL;
import java.text.SimpleDateFormat;

public class Utils {
    public static final String IPV4_REGEX = "^(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\." +
            "(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\." +
            "(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\." +
            "(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$";

    public static final Integer PORT_MIN = 5000;
    public static final Integer PORT_MAX = 5050;
    public static final String USER_DATA_PATH = "resources/user_data.json";
    public static final String HISTORY_PATH = "resources/history.json";
    public static final SimpleDateFormat TIMESTAMP_FORMAT = new SimpleDateFormat("yyyy-MM-dd@HH:mm:ss");

    public static final String getFileFromResources(String fileName) {
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
