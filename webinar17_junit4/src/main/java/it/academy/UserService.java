package it.academy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * UserService – a simple in-memory user management service.
 * Used to demonstrate test lifecycle (@Before / @After) and collection assertions.
 */
public class UserService {

    private final List<String> users = new ArrayList<>();

    public void addUser(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("User name must not be null or empty");
        }
        users.add(name);
    }

    public boolean removeUser(String name) {
        return users.remove(name);
    }

    public boolean containsUser(String name) {
        return users.contains(name);
    }

    public int getUserCount() {
        return users.size();
    }

    public List<String> getAllUsers() {
        return Collections.unmodifiableList(users);
    }

    public void clear() {
        users.clear();
    }
}

