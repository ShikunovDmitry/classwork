Test Requirements
📌 Part 1 — VehicleTest
1.1 Basic Tests
Vehicle is created with correct licensePlate (uppercased), type, ownerName
toString() contains license plate, type, and owner name
1.2 Exception Tests — Constructor

| Input | Expected Exception |
| :-- | :-- |
| licensePlate = null | IllegalArgumentException |
| licensePlate = "  " | IllegalArgumentException |
| type = null | IllegalArgumentException |
| ownerName = null | IllegalArgumentException |
| ownerName = "" | IllegalArgumentException |


1.3 Parameterized — License Plate Normalization
"abc-123, ABC-123"
"xyz 999, XYZ 999"
"  AA1,   AA1"
Verify that getLicensePlate() always returns the uppercased value

Part 2 — ParkingLotTest
2.1 Lifecycle Setup
Use @BeforeEach — create a ParkingLot("Central Park", 5, 10, 3)

2.2 Basic Tests
Lot name is "Central Park"
Total spots: MOTORCYCLE=5, CAR=10, TRUCK=3
Available spots equal total spots on fresh lot
getTotalParkedVehicles() returns 0 on fresh lot
2.3 Park & Leave Tests
After parking a CAR → available CAR spots decrease by 1
After parking and leaving a CAR → available spots restore to original
isVehicleParked() returns true after parking
isVehicleParked() returns false after leaving
getTotalParkedVehicles() increments correctly after each park
2.4 Exception Tests

| Scenario | Expected Exception |
| :-- | :-- |
| Park same vehicle twice | VehicleAlreadyParkedException |
| Leave a vehicle not parked | VehicleNotFoundException |
| Fill all CAR spots, then park one more CAR | ParkingLotFullException |
| park(null) | IllegalArgumentException |
| leave(null) | IllegalArgumentException |
| getAvailableSpots(null) | IllegalArgumentException |
| new ParkingLot(null, 5, 5, 5) | IllegalArgumentException |
| new ParkingLot("X", -1, 5, 5) | IllegalArgumentException |

For ParkingLotFullException — verify exception.getVehicleType() equals CAR
💡 For VehicleNotFoundException — verify exception.getLicensePlate() equals the correct plate

2.5 Parameterized — Park Multiple Vehicle Types
Use @ParameterizedTest + @EnumSource(VehicleType.class):

For each VehicleType, park one vehicle and verify available spots decreased by exactly 1

2.6 Parameterized — isVehicleParked with Various Plates

"ABC-001", "XYZ-999", "TRUCK01", "MOTO22"

Park each vehicle, verify isVehicleParked() returns true
After leaving, verify isVehicleParked() returns false

Part 3 — FeeCalculatorTest
3.1 Setup
Use @BeforeAll — create a single shared FeeCalculator instance

3.2 Parameterized — Basic Fee Calculation

// VehicleType, hours, expectedFee
"MOTORCYCLE, 0,   0.0"
"MOTORCYCLE, 1,   1.5"
"MOTORCYCLE, 10, 15.0"
"CAR,        0,   0.0"
"CAR,        1,   3.0"
"CAR,        8,  24.0"
"TRUCK,      1,   6.0"
"TRUCK,      5,  30.0"

3.3 Parameterized — Daily Rate Boundary (24h+)
// VehicleType, hours, expectedFee
"MOTORCYCLE, 24, 15.0"   // 1 full day = daily rate
"MOTORCYCLE, 25, 16.5"   // 1 day + 1 hour
"CAR,        24, 25.0"   // 1 full day
"CAR,        48, 50.0"   // 2 full days
"TRUCK,      24, 50.0"   // 1 full day
"TRUCK,      26, 62.0"   // 1 day + 2 hours

3.4 Parameterized — Fee with Discount
// VehicleType, hours, discount%, expectedFee
"CAR,   10,   0,  30.0"
"CAR,   10,  50,  15.0"
"CAR,   10, 100,   0.0"
"TRUCK,  5,  20,  24.0"

3.5 Exception Tests
| Scenario | Expected Exception |
| :-- | :-- |
| calculateFee(null, 5) | IllegalArgumentException |
| calculateFee(CAR, -1) | InvalidDurationException |
| calculateFee(CAR, -99) | InvalidDurationException |
| calculateFeeWithDiscount(CAR, 5, -1) | IllegalArgumentException |
| calculateFeeWithDiscount(CAR, 5, 101) | IllegalArgumentException |
| getHourlyRate(null) | IllegalArgumentException |