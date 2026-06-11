🅿️ Low-Level Design (LLD) — Parking Lot Management System
A production-ready Parking Lot Management System built with Java 21 and Spring Boot 4, demonstrating comprehensive object-oriented design principles and classic design patterns.


📋 Table of Contents
Overview
Tech Stack
Project Structure
Architecture
Core Components
Key Features
Design Patterns
Pricing Structure
Getting Started
Running the Demo
API Usage Examples
Database Design
Future Enhancements


Overview
This project is a full-featured Parking Lot Management System that handles:

Vehicle entry and exit with ticket generation
Multi-level parking lot management
Parking spot allocation by vehicle type
Payment processing (multiple payment methods)
Real-time availability tracking and display board
Revenue and parking reports
LRU Cache implementation (bonus data structure)


Tech Stack
Technology
Version
Java
21
Spring Boot
4.0.6
Maven
Wrapper
Build Tool
Maven
Language
100% Java



Project Structure
src/main/java/com/example/lld/

├── LldApplication.java                  # Spring Boot Main Application

├── ParkingLotDemo.java                  # Demo/Test Runner

│

├── dto/

│   ├── parking/

│   │   ├── Vehicle.java                 # Vehicle entity (RC, type, spot needed)

│   │   ├── ParkingSpot.java             # Individual spot with status management

│   │   ├── Level.java                   # Parking level containing multiple spots

│   │   ├── Ticket.java                  # Entry/exit ticket with timing & payment info

│   │   ├── Payment.java                 # Payment details (method, status, amount)

│   │   └── DisplayBoard.java            # Real-time spot availability display

│   └── lru/

│       └── LRUNode.java                 # Node for LRU Cache implementation

│

├── services/

│   ├── parkingLotService.java           # Core business logic

│   ├── ParkingLot.java                  # Main controller / orchestrator

│   ├── EntranceGate.java                # Vehicle entry & ticket issuance

│   ├── ExitGate.java                    # Vehicle exit & payment processing

│   ├── ParkingAttendant.java            # Parking & revenue reports

│   ├── ParkingRateCalculator.java       # Fee calculation based on duration

│   └── LRUCacheService.java             # LRU Cache service implementation

│

└── enums/

    └── parking/

        ├── VehicleType.java             # CAR, BIKE, BUS, TRUCK

        ├── SpotType.java                # COMPACT, REGULAR, LARGE, EXTRA_LARGE

        ├── LevelType.java               # ZERO, ONE, TWO

        ├── SpotStatus.java              # AVAILABLE, OCCUPIED, RESERVED, MAINTENANCE

        ├── TicketStatus.java            # ACTIVE, PAID, EXPIRED, CANCELLED

        ├── PaymentStatus.java           # PENDING, COMPLETED, FAILED, REFUNDED

        └── PaymentMethod.java           # CASH, CREDIT_CARD, DEBIT_CARD, UPI, WALLET, CHEQUE


Architecture
The system follows a layered service architecture:

┌────────────────────────────────────────────┐

│              ParkingLot (Controller)        │

└──────┬──────────────┬───────────┬──────────┘

       │              │           │

  EntranceGate   ExitGate   ParkingAttendant

       │              │

       └──────┬───────┘

              │

      parkingLotService  ◄──── ParkingRateCalculator

              │

    ┌─────────┼─────────┐

    │         │         │

  Level[]  Ticket    Payment

    │

  ParkingSpot[]

Entry Flow: Vehicle → EntranceGate → parkingLotService → Level → ParkingSpot → Ticket

Exit Flow: Ticket → ExitGate → parkingLotService → Payment → ParkingSpot Released


Core Components
DTOs
Class
Description
Vehicle
Holds RC number, vehicle type, and required spot type
ParkingSpot
Individual spot with status (AVAILABLE / OCCUPIED / RESERVED / MAINTENANCE)
Level
A floor in the parking lot, contains an array of ParkingSpots
Ticket
Issued on entry; holds vehicle, timestamps, status, and fee amount
Payment
Records payment method, status, amount, and timestamp
DisplayBoard
Maps each SpotType to its live available count

Services
Service
Responsibility
ParkingLot
Orchestrates all subsystems; entry point for operations
parkingLotService
Core logic: spot allocation, ticket management, payment
EntranceGate
Handles vehicle entry and ticket issuance
ExitGate
Processes vehicle exit and triggers payment
ParkingAttendant
Generates parking availability and revenue reports
ParkingRateCalculator
Calculates fees based on time; enforces daily cap
LRUCacheService
Generic LRU Cache (bonus data structure implementation)

Enums
Enum
Values
VehicleType
CAR, BIKE, BUS, TRUCK
SpotType
COMPACT, REGULAR, LARGE, EXTRA_LARGE
LevelType
ZERO, ONE, TWO
SpotStatus
AVAILABLE, OCCUPIED, RESERVED, MAINTENANCE
TicketStatus
ACTIVE, PAID, EXPIRED, CANCELLED
PaymentStatus
PENDING, COMPLETED, FAILED, REFUNDED
PaymentMethod
CASH, CREDIT_CARD, DEBIT_CARD, UPI, WALLET, CHEQUE



Key Features
1. Vehicle Parking
Automatically finds the best available spot matching the vehicle's required spot type across all levels.

ParkingLot parkingLot = new ParkingLot("PL-001", "123 Main Street", 3, 10);

Vehicle car = new Vehicle("ABC123", SpotType.COMPACT, VehicleType.CAR);

Ticket ticket = parkingLot.parkVehicle(car);
2. Vehicle Exit & Payment
Calculates fee based on duration. Enforces a maximum daily rate cap.

Payment payment = parkingLot.exitVehicle(ticket.getTicketNumber());

// Fee = hours × ₹50, capped at ₹500/day
3. Real-Time Display Board
Shows live available spot counts by spot type.

parkingLot.displayBoard();

// COMPACT:     5 available

// REGULAR:     3 available

// LARGE:       2 available

// EXTRA_LARGE: 4 available
4. Reports
parkingLot.generateParkingReport();  // Spot availability across all levels

parkingLot.generateRevenueReport();  // Total revenue collected


Design Patterns
Pattern
Where Used
Singleton
ParkingLot instance management
Factory
Spot and Level creation
Strategy
Pluggable parking rate calculation logic
Observer
DisplayBoard updates on every entry/exit event
Service Layer
Clear separation of business logic from controllers



Pricing Structure
Duration
Rate
Per Hour
₹50
Max Daily
₹500
Monthly (planned)
₹5,000



Getting Started
Prerequisites
Java 21+
Maven 3.8+
Clone the Repository
git clone https://github.com/Rohitash-Samota/lld.git

cd lld
Compile
mvn clean compile
Build
mvn clean package


Running the Demo
Run with Spring Boot
mvn spring-boot:run
Run the Demo Class
Uncomment the demo call in LldApplication.java, or run directly:

java -cp target/classes com.example.lld.ParkingLotDemo
What the Demo Covers
Parking multiple vehicles — Car, Bike, Bus
Checking available spots on the display board
Vehicle exit and fee calculation
Generating parking and revenue reports


API Usage Examples
// Initialize a 3-level parking lot with 10 spots per level

ParkingLot lot = new ParkingLot("PL-001", "MG Road, Jaipur", 3, 10);

// Park a car

Vehicle car = new Vehicle("RJ14CD1234", SpotType.COMPACT, VehicleType.CAR);

Ticket carTicket = lot.parkVehicle(car);

// Park a bike

Vehicle bike = new Vehicle("RJ14AB5678", SpotType.COMPACT, VehicleType.BIKE);

Ticket bikeTicket = lot.parkVehicle(bike);

// Check availability

lot.displayBoard();

// Exit and pay

Payment payment = lot.exitVehicle(carTicket.getTicketNumber());

// Reports

lot.generateParkingReport();

lot.generateRevenueReport();


Database Design
For a production deployment, the following relational schema would be used:

Table
Purpose
parking_lot
Lot metadata (name, address, capacity)
level
Floors within a lot
parking_spot
Individual spots per floor
vehicle
Vehicle registry (RC, type)
ticket
Entry/exit records with timestamps
payment
Payment records per ticket
parking_attendant
Staff accounts and access control



Constraints & Validations
A vehicle can only park in a spot matching its required SpotType
Spot status transitions are strictly enforced
Parking fee respects the maximum daily rate cap
Tickets are invalidated (status → PAID) after a successful exit
Duplicate RC number detection is planned for a future release


Future Enhancements
REST API endpoints for mobile and web integration
Database persistence with JPA / Hibernate
Real-time spot updates via WebSocket
Monthly subscription management
Advance reservation system
Dedicated handicap spot management
Multi-currency payment support
Third-party payment gateway integration
Vehicle compaction alerts
Electric vehicle (EV) charging station support


Documentation Files
File
Description
PARKING_LOT_LLD.md
Detailed LLD design document
ARCHITECTURE_DIAGRAM.md
Class relationships & sequence diagrams
FILE_INVENTORY.md
Full file listing with descriptions
QUICKSTART.md
Quick setup and run guide



Author
Rohitash Samota

GitHub: @Rohitash-Samota



Version: 1.0  |  Status: Production Ready  |  Language: Java 21  |  Framework: Spring Boot 4