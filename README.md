# LLD Java Systems

This repository contains several low-level design (LLD) Java projects implemented as a single Spring Boot workspace. The current branch focuses on inventory management and includes parking lot, booking, rate limiter, LRU cache, and inventory modules.

## Repository Overview

### Main Modules
- **Parking Lot System**: Multi-level parking management with vehicle entry/exit, ticketing, payment, and dashboard reporting.
- **Movie Booking System**: Theater/movie/show/seat booking with search and pricing strategy support.
- **LRU Cache Implementation**: A simple least-recently-used cache service with put/get behavior and eviction.
- **Inventory Management**: Warehouse, stock, product type, and location DTOs for basic inventory domain modeling.
- **Rate Limiter**: Pluggable rate limiting engine supporting token bucket and sliding window algorithms with key strategy abstractions.

### Key Entry Points
- `src/main/java/com/example/lld/LldApplication.java` — Spring Boot application launcher.
- `src/main/java/com/example/lld/TestRunnerParking.java` — demo runner for the parking system.
- `src/main/java/com/example/lld/TestRunnerBooking.java` — demo runner for the booking system.
- `src/main/java/com/example/lld/TestRunnerLRUCache.java` — demo runner for the LRU cache.

## Build & Run

### Prerequisites
- Java 21
- Maven

### Build
```bash
cd /Users/rohitsamota/Documents/Projects/lld
./mvnw clean package
```

### Run Spring Boot App
```bash
./mvnw spring-boot:run
```

### Run Demo Runners
Open `LldApplication.java` and uncomment the desired demo call, or run the individual demo classes from your IDE.

## Project Structure

```text
src/main/java/com/example/lld/
├── LldApplication.java
├── TestRunnerBooking.java
├── TestRunnerLRUCache.java
├── TestRunnerParking.java
├── dto/
│   ├── booking/
│   ├── lru/
│   └── parking/
├── enums/
│   ├── booking/
│   └── parking/
├── factory/
│   ├── booking/
│   └── parking/
├── interfaces/
│   └── booking/
├── inventoryManagement/
│   ├── dto/
│   ├── enums/
│   └── services/
├── rateLimiter/
│   ├── config/
│   ├── dto/
│   ├── enums/
│   ├── factory/
│   ├── implementation/
│   ├── interfaces/
│   ├── services/
│   └── strategy/
├── repo/
│   └── booking/
├── services/
│   ├── booking/
│   └── parking/
└── strategy/
    ├── booking/
    └── parking/
```

## Module Summaries

### Parking Lot System
- Supports multi-level parking with spot type awareness.
- Includes `EntranceGate`, `ExitGate`, `ParkingService`, `TicketService`, `PaymentService`, and `DashboardService`.
- Uses strategy and factory patterns for pricing.

### Movie Booking System
- Handles movies, theaters, screens, shows, seats, pricing rules, and ticket booking.
- Includes search strategies for city, movie title, and theater name.
- Uses an in-memory booking repository.

### LRU Cache
- `LRUCacheService` implements a cache with eviction order based on least-recently-used access.
- Demonstrated in `TestRunnerLRUCache`.

### Inventory Management
- Domain objects for `WareHouse`, `Stock`, `location`, and `ProductType`.
- Provides a base model for inventory-related features.

### Rate Limiter
- Pluggable implementation of request limiting using `TokenBucketRateLimiter` and `SlidingWindowLogRateLimiter`.
- Supports multiple keying strategies such as IP, user, and device.
- Includes configuration and factory classes.

## Notes
- The repository currently uses Spring Boot and is set up for easy expansion.
- Existing documentation files include `QUICKSTART.md`, `HELP.md`, and `PARKING_LOT_LLD.md`.
- The root `pom.xml` declares Spring Boot 4.0.6 and the `spring-boot-starter-webmvc` dependency.

## Recommended Next Steps
- Add runnable demo classes for inventory and rate limiter scenarios.
- Add tests for booking, parking, inventory, and rate limiter modules.
- Extend the Spring Boot application with controllers for REST APIs.

---

**LLD Projects**: Parking Lot, Movie Booking, LRU Cache, Inventory Management, Rate Limiter
