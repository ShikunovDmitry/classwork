// src/main/java/parking/exception/ParkingLotFullException.java
package parking.exception;

import parking.model.VehicleType;

public class ParkingLotFullException extends RuntimeException {
    private final VehicleType vehicleType;

    public ParkingLotFullException(VehicleType vehicleType) {
        super(String.format("No available spots for vehicle type: %s", vehicleType));
        this.vehicleType = vehicleType;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }
}