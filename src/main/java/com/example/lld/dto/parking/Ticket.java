package com.example.lld.dto.parking;

import java.time.LocalDateTime;
import java.util.UUID;

import com.example.lld.enums.parking.TicketStatus;

public class Ticket {

    private String ticketNumber;
    private Vehicle vehicle;
    private LocalDateTime issueTime;
    private LocalDateTime exitTime;
    private TicketStatus status;
    private double amount;

    public Ticket(String ticketNumber,
            Vehicle vehicle,
            LocalDateTime issueTime,
            TicketStatus status) {

        this.ticketNumber = ticketNumber;
        this.vehicle = vehicle;
        this.issueTime = issueTime;
        this.status = status;
        this.amount = 0;
    }

    public String getTicketNumber() {
        return ticketNumber;
    }

    public void setTicketNumber(String ticketNumber) {
        this.ticketNumber = ticketNumber;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public LocalDateTime getIssueTime() {
        return issueTime;
    }

    public void setIssueTime(LocalDateTime issueTime) {
        this.issueTime = issueTime;
    }

    public LocalDateTime getExitTime() {
        return exitTime;
    }

    public void setExitTime(LocalDateTime exitTime) {
        this.exitTime = exitTime;
    }

    public TicketStatus getStatus() {
        return status;
    }

    public void setStatus(TicketStatus status) {
        this.status = status;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String createTicketId(Vehicle vehicle) {
        String randomId = UUID.randomUUID().toString().substring(0, 6).toUpperCase();
        return "TC-" + vehicle.getRCNumber() + '-' + randomId;
    }
}
