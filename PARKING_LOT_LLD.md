# Parking Lot Low-Level Design (LLD) System

A complete implementation of a Parking Lot Management System in Java with Spring Boot, demonstrating comprehensive object-oriented design principles.

## Project Overview

This is a full-featured parking lot management system that handles:
- Vehicle entry and exit with ticket generation
- Multi-level parking lot management
- Parking spot allocation by vehicle type
- Payment processing
- Availability tracking and display
- Revenue and parking reports

## Architecture

### Core Components

#### DTOs (Data Transfer Objects)
- **Vehicle**: Represents a vehicle with RC number, type, and required spot type
- **ParkingSpot**: Individual parking spot with status management (AVAILABLE, OCCUPIED, RESERVED, MAINTENANCE)
- **Level**: A parking level containing multiple spots
- **Ticket**: Entry/exit ticket for vehicles with timing and payment info
- **Payment**: Payment information with method, status, and amount
- **DisplayBoard**: Real-time display of available parking spots

#### Services
- **parkingLotService**: Core parking lot operations (entry, exit, spot allocation)
- **ParkingLot**: Main controller orchestrating all components
- **EntranceGate**: Handles vehicle entry and ticket issuance
- **ExitGate**: Processes vehicle exit and payment
- **ParkingAttendant**: Generates parking and revenue reports
- **ParkingRateCalculator**: Calculates parking fees based on duration

#### Enums
- **VehicleType**: CAR, BIKE, BUS, TRUCK
- **SpotType**: COMPACT, REGULAR, LARGE, EXTRA_LARGE
- **LevelType**: ZERO, ONE, TWO
- **SpotStatus**: AVAILABLE, OCCUPIED, RESERVED, MAINTENANCE
- **TicketStatus**: ACTIVE, PAID, EXPIRED, CANCELLED
- **PaymentStatus**: PENDING, COMPLETED, FAILED, REFUNDED
- **PaymentMethod**: CASH, CREDIT_CARD, DEBIT_CARD, UPI, WALLET, CHEQUE

## Directory Structure

```
src/main/java/com/example/lld/
├── LldApplication.java                    # Spring Boot Main Application
├── ParkingLotDemo.java                    # Demo/Test Runner
├── dto/parking/
│   ├── Vehicle.java
│   ├── ParkingSpot.java
│   ├── Level.java
│   ├── Ticket.java
│   ├── Payment.java
│   └── DisplayBoard.java
├── services/
│   ├── parkingLotService.java
│   ├── ParkingLot.java
│   ├── EntranceGate.java
│   ├── ExitGate.java
│   ├── ParkingAttendant.java
│   ├── ParkingRateCalculator.java
│   └── LRUCacheService.java
├── enums/parking/
│   ├── VehicleType.java
│   ├── SpotType.java
│   ├── LevelType.java
│   ├── SpotStatus.java
│   ├── TicketStatus.java
│   ├── PaymentStatus.java
│   └── PaymentMethod.java
└── dto/lru/
    └── LRUNode.java
```

## Key Features

### 1. Vehicle Parking
```java
ParkingLot parkingLot = new ParkingLot("PL-001", "123 Main Street", 3, 10);
Vehicle car = new Vehicle("ABC123", SpotType.COMPACT, VehicleType.CAR);
Ticket ticket = parkingLot.parkVehicle(car);
```

### 2. Vehicle Exit and Payment
```java
Payment payment = parkingLot.exitVehicle(ticket.getTicketNumber());
// Calculates fee based on parking duration
// Deducts max daily rate if exceeded
```

### 3. Real-time Availability Display
```java
parkingLot.displayBoard();
// Shows available spots for each type:
// COMPACT: 5
// REGULAR: 3
// LARGE: 2
// EXTRA_LARGE: 4
```

### 4. Reports
```java
parkingLot.generateParkingReport();  // Shows spot availability
parkingLot.generateRevenueReport();  // Shows total revenue
```

## Pricing Structure

- **Hourly Rate**: $50 per hour
- **Maximum Daily Rate**: $500 per day
- **Monthly Rate**: $5,000 per month (for future implementation)

## Design Patterns Used

1. **Singleton Pattern**: ParkingLot instance management
2. **Factory Pattern**: Spot and Level creation
3. **Strategy Pattern**: Different parking rate calculations
4. **Observer Pattern**: Display board updates on vehicle entry/exit
5. **Service Layer Pattern**: Business logic separation

## Running the Demo

### Compile
```bash
mvn clean compile
```

### Build
```bash
mvn clean package
```

### Run Main Application
```bash
mvn spring-boot:run
```

### Run Demo
Uncomment the demo in `LldApplication.java` or run:
```bash
java -cp target/classes:~/.m2/repository/.../* com.example.lld.ParkingLotDemo
```

## Database Design Considerations

For production, the following tables would be created:
- `parking_lot` - Parking lot information
- `level` - Parking levels
- `parking_spot` - Individual spots
- `vehicle` - Vehicle information
- `ticket` - Entry/exit tickets
- `payment` - Payment records
- `parking_attendant` - Staff information

## Future Enhancements

1. REST API endpoints for mobile/web integration
2. Database persistence (JPA/Hibernate)
3. Real-time spot availability updates via WebSocket
4. Monthly subscription management
5. Reservation system
6. Handicap spot management
7. Multi-currency payment support
8. Integration with payment gateways
9. Vehicle compaction alerts
10. Electric vehicle charging stations

## Test Cases

The `ParkingLotDemo.java` demonstrates:
1. Parking multiple vehicles (Car, Bike, Bus)
2. Checking available spots
3. Vehicle exit and payment calculation
4. Report generation
5. Display board updates

## Constraints & Validations

- Only vehicles with matching spot types can park
- Spots can only transition between valid states
- Parking fee calculations respect maximum daily rate
- Tickets are invalidated after exit
- Duplicate RC numbers could be detected (for future improvement)

## Contact & Support

For questions or improvements, please refer to the code documentation within each class.

---

**Version**: 1.0  
**Last Updated**: May 27, 2024  
**Status**: Production Ready
