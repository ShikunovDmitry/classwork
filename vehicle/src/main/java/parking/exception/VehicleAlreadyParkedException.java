// src/main/java/parking/exception/VehicleAlreadyParkedException.java
package parking.exception;

public class VehicleAlreadyParkedException extends RuntimeException {
    public VehicleAlreadyParkedException(String licensePlate) {
        super(String.format("Vehicle '%s' is already parked in this lot", licensePlate));
    }
}