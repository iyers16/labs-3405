public class Serveur {
    public static void main(String[] args) {
        System.out.println(ValidationService.isValidIpv4("123.123.12.12"));

        // test if user admin is in the json:

        System.out.println(ValidationService.isValidUser("admin", "pwsdfd"));
    }
}
