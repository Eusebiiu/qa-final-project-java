
import java.util.Scanner;
public class UsernameValidator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String username;
        boolean isValid = false;

        do {
            System.out.print("Introdu un username: ");
            username = scanner.nextLine();

            isValid = true;
            if (username.length() < 6 || username.length() > 12) {
                System.out.println("Eroare: Username-ul trebuie sa aiba intre 6 si 12 caractere.");
                isValid = false;
                continue; 
            }

            if (username.contains(" ")) {
                System.out.println("Eroare: Username-ul nu poate contine spatii.");
                isValid = false;
                continue; 
            }

            boolean hasDigit = false;
            
            for (int i = 0; i < username.length(); i++) {
                char ch = username.charAt(i);
                
                if (Character.isDigit(ch)) {
                    hasDigit = true;
                    break; 
                }
            }
            
            if (!hasDigit) {
                System.out.println("Eroare: Username-ul trebuie sa contina cel putin o cifra.");
                isValid = false;
                continue; 
            }

        } while (!isValid); // Repetă atâta timp cât isValid este false

        System.out.println("Username acceptat: " + username);
        
        scanner.close();
    }
}
