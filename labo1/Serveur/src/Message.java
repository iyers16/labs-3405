import java.util.Date;

/*
 * POJO pour recevoir, ecrire et stocker des messages
 */
public class Message {
    private String username;
    private String ip;
    private String port;
    private String timestamp;
    private String message;

    @Override
    public String toString() {
        return String.format("[%s - %s:%s - %s]: %s", this.username, this.ip, this.port, this.timestamp, this.message);
    }

    public Message(String username, String ip, Integer port, Date timestamp, String message) {
        this.username = username;
        this.ip = ip;
        this.port = String.valueOf(port);
        this.timestamp = Utils.TIMESTAMP_FORMAT.format(timestamp);
        this.message = message;
    }

    // getters & setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = String.valueOf(port);
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = Utils.TIMESTAMP_FORMAT.format(timestamp);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
