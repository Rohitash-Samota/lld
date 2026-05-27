# Parking Lot LLD - System Architecture Diagram

## Class Relationships

```
┌─────────────────────────────────────────────────────────────────┐
│                       PARKING LOT SYSTEM                         │
└─────────────────────────────────────────────────────────────────┘

┌─────────────────┐
│   ParkingLot    │ (Main Controller)
└────────┬────────┘
         │
    ┌────┴──────────┬──────────────┬──────────────┐
    │               │              │              │
┌───▼────┐  ┌──────▼──────┐  ┌───▼────┐  ┌─────▼──────┐
│ Entrance│  │ Exit Gate   │  │Display │  │  Parking   │
│  Gate   │  │             │  │ Board  │  │  Attendant │
└────┬────┘  └──────┬──────┘  └───┬────┘  └─────┬──────┘
     │              │             │             │
     └──────────────┼─────────────┼─────────────┘
                    │
          ┌─────────▼──────────┐
          │  parkingLotService │
          │  (Core Business    │
          │   Logic)           │
          └─────────┬──────────┘
                    │
      ┌─────────────┼─────────────┐
      │             │             │
  ┌───▼────┐  ┌────▼────┐  ┌────▼─────┐
  │ Level[]│  │ Ticket  │  │ Payment   │
  │        │  │ Manager │  │ Manager   │
  └───┬────┘  └────┬────┘  └────┬─────┘
      │            │            │
  ┌───▼──────────┐ │ ┌──────────▼─────┐
  │ ParkingSpot[]│ │ │ PaymentRecord   │
  │              │ │ └────────────────┘
  └──────────────┘ │
                   │
              ┌────▼──────┐
              │  Ticket   │
              └───────────┘

ENTITIES:
---------

┌─────────────────────────────────────────────────────────────┐
│                        Vehicle                              │
├─────────────────────────────────────────────────────────────┤
│ - rcNumber: String                                          │
│ - vehicleType: VehicleType (CAR, BIKE, BUS, TRUCK)         │
│ - spotNeeded: SpotType (COMPACT, REGULAR, LARGE, EXTRA)   │
└─────────────────────────────────────────────────────────────┘

┌─────────────────────────────────────────────────────────────┐
│                     ParkingSpot                             │
├─────────────────────────────────────────────────────────────┤
│ - spotId: String                                            │
│ - vehicle: Vehicle                                          │
│ - row: int                                                  │
│ - column: int                                               │
│ - spotNumber: int                                           │
│ - spotType: SpotType                                        │
│ - status: SpotStatus (AVAILABLE, OCCUPIED, RESERVED, MAINT) │
├─────────────────────────────────────────────────────────────┤
│ + parkVehicle(vehicle): void                                │
│ + unparkVehicle(): Vehicle                                  │
│ + isAvailable(): boolean                                    │
└─────────────────────────────────────────────────────────────┘

┌─────────────────────────────────────────────────────────────┐
│                       Level                                 │
├─────────────────────────────────────────────────────────────┤
│ - levelId: String                                           │
│ - level: LevelType (ZERO, ONE, TWO)                        │
│ - spots: ParkingSpot[]                                      │
│ - totalSpots: int                                           │
│ - availableSpots: int                                       │
├─────────────────────────────────────────────────────────────┤
│ + findAvailableSpot(spotType): ParkingSpot                 │
│ + parkVehicle(spot, vehicle): void                         │
│ + unparkVehicle(spot): void                                │
│ + getAvailableSpots(): List<ParkingSpot>                   │
└─────────────────────────────────────────────────────────────┘

┌─────────────────────────────────────────────────────────────┐
│                       Ticket                                │
├─────────────────────────────────────────────────────────────┤
│ - ticketNumber: String                                      │
│ - vehicle: Vehicle                                          │
│ - issueTime: LocalDateTime                                  │
│ - exitTime: LocalDateTime                                   │
│ - status: TicketStatus                                      │
│ - amount: double                                            │
└─────────────────────────────────────────────────────────────┘

┌─────────────────────────────────────────────────────────────┐
│                       Payment                               │
├─────────────────────────────────────────────────────────────┤
│ - paymentId: String                                         │
│ - ticket: Ticket                                            │
│ - amount: double                                            │
│ - paymentMethod: PaymentMethod                              │
│ - status: PaymentStatus                                     │
│ - paymentTime: LocalDateTime                                │
└─────────────────────────────────────────────────────────────┘

┌─────────────────────────────────────────────────────────────┐
│                    DisplayBoard                             │
├─────────────────────────────────────────────────────────────┤
│ - boardId: String                                           │
│ - availableSpotsCount: Map<SpotType, Integer>               │
├─────────────────────────────────────────────────────────────┤
│ + updateAvailableSpots(spotType, count): void              │
│ + getAvailableSpotsCount(spotType): int                    │
│ + displayBoard(): void                                      │
└─────────────────────────────────────────────────────────────┘

FLOW DIAGRAMS:
--------------

ENTRY FLOW:
    Vehicle → EntranceGate → parkingLotService → Level → ParkingSpot
       │                                                        │
       └────────────────────────────────────────────────────────┘
                            Ticket Issued

EXIT FLOW:
    Ticket → ExitGate → parkingLotService → Payment Calculation
       │                                             │
       └─────────────────────────────────────────────┘
                   ParkingSpot Released
                   Payment Confirmed
```

## Sequence Diagram - Vehicle Parking

```
User          ParkingLot      EntranceGate     Service          Level          Spot
 │                │                │              │               │             │
 ├──parkVehicle──▶│                │              │               │             │
 │                ├──issueTicket──▶│              │               │             │
 │                │                ├─parkVehicle─▶│               │             │
 │                │                │              ├─findAvailable▶│             │
 │                │                │              │◀─available───┤             │
 │                │                │              ├─parkVehicle──▶│             │
 │                │                │              │               ├─occupied───▶│
 │                │                │◀─Ticket────┤               │             │
 │                │◀─Ticket───────┤              │               │             │
 │◀─Ticket───────┤                │              │               │             │
```

## Sequence Diagram - Vehicle Exit

```
User          ParkingLot      ExitGate         Service          Spot
 │                │               │              │               │
 ├──exitVehicle──▶│               │              │               │
 │                ├─processExit──▶│              │               │
 │                │                ├─exitVehicle▶│               │
 │                │                │              ├─unparkVehicle▶│
 │                │                │              │◀─available───┤
 │                │                │ ◀─Payment──┤               │
 │                │ ◀─Payment─────┤              │               │
 │ ◀─Payment─────┤                │              │               │
```
