package com.Eusebiiu.homework;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        UserRepository userRepository = new UserRepository();
        User validUser1 = new User("testuser", 25);

        try {
            userRepository.addUser(validUser1);
        } catch (InvalidUserDataException e) {
            System.err.println(" EROARE ADAUGARE: " + e.getMessage());
        }

        AdminUser adminUser = new AdminUser("admin", 30, "full_access");
        try {
            userRepository.addUser(adminUser);
        } catch (InvalidUserDataException e) {
            System.err.println(" EROARE ADAUGARE: " + e.getMessage());
        }

        User invalidUserShortUsername = new User("ab", 22);
        try {
            userRepository.addUser(invalidUserShortUsername);
        } catch (InvalidUserDataException e) {
            System.err.println(" EROARE ADAUGARE (Prea Scurt): " + e.getMessage());
        }

        User invalidUserNegativeAge = new User("baduser", -5);
        try {
            userRepository.addUser(invalidUserNegativeAge);
        } catch (InvalidUserDataException e) {
            System.err.println(" EROARE ADAUGARE (Vârstă Negativă): " + e.getMessage());
        }

        try {
            userRepository.addUser(null);
        } catch (InvalidUserDataException e) {
            System.err.println(" EROARE ADAUGARE (Null): " + e.getMessage());
        }

        System.out.println("\n--- Afișare Utilizatori Adăugați cu Succes ---");
        List<User> successfulUsers = userRepository.getUsers();
        if (successfulUsers.isEmpty()) {
            System.out.println("Nu a fost adăugat niciun utilizator.");
        } else {
            for (User user : successfulUsers) {
                System.out.println(user);
            }
            System.out.println("\n--- Afisare lista errori ---");
        }
    }
}