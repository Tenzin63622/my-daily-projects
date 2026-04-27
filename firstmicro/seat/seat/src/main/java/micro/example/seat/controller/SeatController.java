package micro.example.seat.controller;

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
    public String lockSeats(@RequestParam List<Long> seatIds,
                            @RequestParam Long userId) {
        seatService.lockSeats(seatIds, userId);
        return "Seats locked successfully";
    }

    // Confirm booking
    @PostMapping("/confirm")
    public String confirm(@RequestParam List<Long> seatIds) {
        seatService.confirmSeats(seatIds);
        return "Booking confirmed";
    }
}