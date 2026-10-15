// src/main/java/parking/ParkingLot.java
package parking;

import parking.exception.ParkingLotFullException;
import parking.exception.VehicleAlreadyParkedException;
import parking.exception.VehicleNotFoundException;
import parking.model.Vehicle;
import parking.model.VehicleType;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {

    private final String name;

    private final Map<VehicleType, Integer> totalSpots;
    private final Map<VehicleType, Integer> availableSpots;
    private final Map<String, VehicleType> parkedVehicles; // licensePlate -> type

    public ParkingLot(String name, int motorcycleSpots, int carSpots, int truckSpots) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Parking lot name cannot be null or blank");
        }
        if (motorcycleSpots < 0 || carSpots < 0 || truckSpots < 0) {
            throw new IllegalArgumentException("Number of spots cannot be negative");
        }
        this.name = name;
        this.totalSpots = new HashMap<>();
        this.availableSpots = new HashMap<>();
        this.parkedVehicles = new HashMap<>();

        totalSpots.put(VehicleType.MOTORCYCLE, motorcycleSpots);
        totalSpots.put(VehicleType.CAR, carSpots);
        totalSpots.put(VehicleType.TRUCK, truckSpots);

        availableSpots.put(VehicleType.MOTORCYCLE, motorcycleSpots);
        availableSpots.put(VehicleType.CAR, carSpots);
        availableSpots.put(VehicleType.TRUCK, truckSpots);
    }

    public void park(Vehicle vehicle) {
        if (vehicle == null) {
            throw new IllegalArgumentException("Vehicle cannot be null");
        }
        String plate = vehicle.getLicensePlate();
        VehicleType type = vehicle.getType();

        if (parkedVehicles.containsKey(plate)) {
            throw new VehicleAlreadyParkedException(plate);
        }
        if (availableSpots.get(type) == 0) {
            throw new ParkingLotFullException(type);
        }

        parkedVehicles.put(plate, type);
        availableSpots.put(type, availableSpots.get(type) - 1);
    }

    public void leave(String licensePlate) {
        if (licensePlate == null || licensePlate.isBlank()) {
            throw new IllegalArgumentException("License plate cannot be null or blank");
        }
        String plate = licensePlate.toUpperCase().trim();

        if (!parkedVehicles.containsKey(plate)) {
            throw new VehicleNotFoundException(plate);
        }

        VehicleType type = parkedVehicles.remove(plate);
        availableSpots.put(type, availableSpots.get(type) + 1);
    }

    public boolean isVehicleParked(String licensePlate) {
        if (licensePlate == null) return false;
        return parkedVehicles.containsKey(licensePlate.toUpperCase().trim());
    }

    public int getAvailableSpots(VehicleType type) {
        if (type == null) throw new IllegalArgumentException("VehicleType cannot be null");
        return availableSpots.get(type);
    }

    public int getTotalSpots(VehicleType type) {
        if (type == null) throw new IllegalArgumentException("VehicleType cannot be null");
        return totalSpots.get(type);
    }

    public int getTotalParkedVehicles() {
        return parkedVehicles.size();
    }

    public String getName() {
        return name;
    }
}