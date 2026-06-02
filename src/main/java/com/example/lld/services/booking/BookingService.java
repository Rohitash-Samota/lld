package com.example.lld.services.booking;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.lld.dto.booking.Seat;
import com.example.lld.dto.booking.Show;
import com.example.lld.dto.booking.Ticket;

@Service
public class BookingService {

    private final ShowService showService;
    private final PricingService pricingService;
    private final PaymentProcessor paymentProcessor;
    private final BookingRepository bookingRepository;

    public BookingService(
            ShowService showService,
            PricingService pricingService,
            PaymentProcessor paymentProcessor,
            BookingRepository bookingRepository) {
        this.showService = showService;
        this.pricingService = pricingService;
        this.paymentProcessor = paymentProcessor;
        this.bookingRepository = bookingRepository;
    }

    public Ticket bookTicket(String showId, char row, int col, boolean weekend,
            boolean festival) {

        Show showOpt = showService.findShowById(showId);
        if (showOpt == null) {
            return null;
        }

        if (showOpt.getScreen() == null) {
            return null;
        }

        Seat seat = findSeat(showOpt, row, col);
        if (seat == null || seat.isBooked()) {
            return null;
        }

        seat.setBooked(true);
        double amount = pricingService.calculateTicketPrice(showOpt, seat, weekend, festival);
        Ticket ticket = TicketFactory.createTicket(showOpt, seat, amount);
        if (!paymentProcessor.processPayment(ticket, amount)) {
            return null;
        }

        bookingRepository.save(ticket);
        return ticket;
    }

    public List<Ticket> getBookings() {
        return bookingRepository.findAll();
    }

    private Seat findSeat(Show show, char row, int col) {
        if (show.getScreen().getSeats() == null) {
            return null;
        }

        for (Seat seat : show.getScreen().getSeats()) {
            if (seat != null && seat.getRow() == row
                    && seat.getColNumber() == col) {
                return seat;
            }
        }
        return null;
    }
}
