package com.example.lld.parking.dto;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.UUID;

import com.example.lld.parking.enums.TicketStatus;

public class Ticket {

    private String ticketNumber;
    private String vehicleNumber;
    private LocalDateTime issueTime;
    private LocalDateTime exitTime;
    private TicketStatus status;
    private double amount;

    public Ticket(String vehicleNumber) {
        this.ticketNumber = generateTicketId(vehicleNumber);
        this.vehicleNumber = vehicleNumber;
        this.issueTime = LocalDateTime.now(ZoneId.of("Asia/Kolkata"));
        this.status = TicketStatus.ACTIVE;
        this.amount = 0;
    }

    public String getTicketNumber() {
        return ticketNumber;
    }

    public void setTicketNumber(String ticketNumber) {
        this.ticketNumber = ticketNumber;
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

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    private String generateTicketId(String RCNumber) {
        return "TICKET-" + RCNumber + "-" + System.currentTimeMillis();
    }

}
