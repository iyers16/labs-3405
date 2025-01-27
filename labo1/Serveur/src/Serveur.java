import java.util.Date;

public class Serveur {
    public static void main(String[] args) {
        System.out.println(ValidationService.isValidIpv4("123.123.12.12"));

        System.out.println(ValidationService.isValidUser("admin", "pwsdfd"));

        System.out.println(new Message("user1", "123.123.123.123", 50001, new Date(), "hello world"));
    }
}
