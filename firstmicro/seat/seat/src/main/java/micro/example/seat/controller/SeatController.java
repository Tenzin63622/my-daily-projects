package micro.example.seat.controller;

import micro.example.seat.dto.ConfirmSeatRequest;
import micro.example.seat.dto.LockSeatRequest;
import micro.example.seat.entity.Seat;
import micro.example.seat.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/seats")
public class SeatController {

    @Autowired
    private SeatService seatService;

    // Add seat
    @PostMapping
    public Seat addSeat(@RequestBody Seat seat) {
        return seatService.addSeat(seat);
    }

    // Get seats by show
    @GetMapping("/show/{showId}")
    public List<Seat> getSeats(@PathVariable Long showId) {
        return seatService.getSeatsByShow(showId);
    }

    // Lock seats
    @PostMapping("/lock")
    public String lockSeats(@RequestBody LockSeatRequest request) {

        seatService.lockSeats(request.getSeatIds(), request.getUserId());
        return "Seats locked successfully";
    }

    // Confirm booking
    @PostMapping("/confirm")
    public String confirm(@RequestBody ConfirmSeatRequest request) {

        seatService.confirmSeats(request.getSeatIds());
        return "Booking confirmed";
    }
}