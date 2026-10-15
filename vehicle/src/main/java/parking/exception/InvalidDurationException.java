// src/main/java/parking/exception/InvalidDurationException.java
package parking.exception;

public class InvalidDurationException extends RuntimeException {
    private final int hours;

    public InvalidDurationException(int hours) {
        super(String.format("Parking duration must be >= 0. Got: %d", hours));
        this.hours = hours;
    }

    public int getHours() {
        return hours;
    }
}