// src/main/java/parking/model/Vehicle.java
package parking.model;

public class Vehicle {

    private final String licensePlate;
    private final VehicleType type;
    private final String ownerName;

    public Vehicle(String licensePlate, VehicleType type, String ownerName) {
        if (licensePlate == null || licensePlate.isBlank()) {
            throw new IllegalArgumentException("License plate cannot be null or blank");
        }
        if (type == null) {
            throw new IllegalArgumentException("Vehicle type cannot be null");
        }
        if (ownerName == null || ownerName.isBlank()) {
            throw new IllegalArgumentException("Owner name cannot be null or blank");
        }
        this.licensePlate = licensePlate.toUpperCase().trim();
        this.type = type;
        this.ownerName = ownerName;
    }

    public String getLicensePlate() { return licensePlate; }
    public VehicleType getType()    { return type; }
    public String getOwnerName()    { return ownerName; }

    @Override
    public String toString() {
        return String.format("Vehicle[%s | %s | owner=%s]", licensePlate, type, ownerName);
    }
}