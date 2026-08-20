package it.academy;

/**
 * DatabaseConnection – simulates a heavyweight resource (e.g. a database connection).
 * Used to demonstrate @BeforeClass / @AfterClass for expensive one-time setup and teardown.
 */
public class DatabaseConnection {

    private boolean connected;

    public void open() {
        System.out.println("[DB] Connection opened");
        this.connected = true;
    }

    public void close() {
        System.out.println("[DB] Connection closed");
        this.connected = false;
    }

    public boolean isConnected() {
        return connected;
    }

    public String query(String sql) {
        if (!connected) {
            throw new IllegalStateException("Not connected to database");
        }
        return "Result for: " + sql;
    }
}

