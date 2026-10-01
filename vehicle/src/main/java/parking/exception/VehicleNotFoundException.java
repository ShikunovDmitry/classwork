// src/main/java/parking/exception/VehicleNotFoundException.java
package parking.exception;

public class VehicleNotFoundException extends RuntimeException {
    private final String licensePlate;

    public VehicleNotFoundException(String licensePlate) {
        super(String.format("Vehicle with license plate '%s' is not parked here", licensePlate));
        this.licensePlate = licensePlate;
    }

    public String getLicensePlate() {
        return licensePlate;
    }
}