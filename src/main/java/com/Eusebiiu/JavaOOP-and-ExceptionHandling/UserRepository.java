package com.Eusebiiu.homework;

import java.util.ArrayList;
import java.util.List;

public class UserRepository {
    private List<User> users = new ArrayList<>();

    public void addUser(User user) throws InvalidUserDataException {
        String username = user.getUsername();
        int age = user.getAge();

        if (user == null) {
            throw new InvalidUserDataException("Eroare: Campul utilizator nu poate fi null.");
        }

        if (username == null || username.length() < 3) {
            throw new InvalidUserDataException("Eroare: Username-ul trebuie să aibă minim 3 caractere.");
        }

        if (age < 0) {
            throw new InvalidUserDataException("Eroare: Vârsta utilizatorului nu poate fi negativă.");
        }

        users.add(user);
        System.out.println("-> Succes: Utilizatorul " + username + " a fost adăugat.");
    }
    public List<User> getUsers() {
        return users;
    }
}