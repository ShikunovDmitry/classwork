package it.academy;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Simple UserService to demonstrate TestNG lifecycle, dependency tests, and more.
 * Simulates a basic in-memory user repository.
 */
public class UserService {

    private final List<String> users = new ArrayList<>();

    public void addUser(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("User name must not be null or blank");
        }
        if (users.contains(name)) {
            throw new IllegalStateException("User already exists: " + name);
        }
        users.add(name);
    }

    public boolean removeUser(String name) {
        return users.remove(name);
    }

    public Optional<String> findUser(String name) {
        return users.stream()
                .filter(u -> u.equalsIgnoreCase(name))
                .findFirst();
    }

    public List<String> getAllUsers() {
        return List.copyOf(users);
    }

    public int getUserCount() {
        return users.size();
    }

    public void clear() {
        users.clear();
    }
}

