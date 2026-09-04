// src/main/java/parking/FeeCalculator.java
package parking;

import parking.exception.InvalidDurationException;
import parking.model.VehicleType;

import java.util.Map;

public class FeeCalculator {

    private static final Map<VehicleType, Double> HOURLY_RATES = Map.of(
            VehicleType.MOTORCYCLE, 1.5,
            VehicleType.CAR,        3.0,
            VehicleType.TRUCK,      6.0
    );

    private static final Map<VehicleType, Double> DAILY_RATES = Map.of(
            VehicleType.MOTORCYCLE, 15.0,
            VehicleType.CAR,        25.0,
            VehicleType.TRUCK,      50.0
    );

    private static final int HOURS_IN_DAY = 24;

    public double calculateFee(VehicleType type, int hours) {
        if (type == null) {
            throw new IllegalArgumentException("Vehicle type cannot be null");
        }
        if (hours < 0) {
            throw new InvalidDurationException(hours);
        }
        if (hours == 0) {
            return 0.0;
        }

        int fullDays  = hours / HOURS_IN_DAY;
        int remaining = hours % HOURS_IN_DAY;

        double dailyFee  = fullDays  * DAILY_RATES.get(type);
        double hourlyFee = remaining * HOURLY_RATES.get(type);

        return dailyFee + hourlyFee;
    }

    public double calculateFeeWithDiscount(VehicleType type, int hours, int discountPercent) {
        if (discountPercent < 0 || discountPercent > 100) {
            throw new IllegalArgumentException(
                    "Discount percent must be between 0 and 100. Got: " + discountPercent);
        }
        double baseFee = calculateFee(type, hours);
        return baseFee * (1.0 - discountPercent / 100.0);
    }

    public double getHourlyRate(VehicleType type) {
        if (type == null) throw new IllegalArgumentException("Vehicle type cannot be null");
        return HOURLY_RATES.get(type);
    }

    public double getDailyRate(VehicleType type) {
        if (type == null) throw new IllegalArgumentException("Vehicle type cannot be null");
        return DAILY_RATES.get(type);
    }
}