package micro.example.booking.controller;

import micro.example.booking.dto.BookingRequest;
import micro.example.booking.entity.Booking;
import micro.example.booking.service.BookingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    //creating
    @PostMapping
    public Booking create(@RequestBody BookingRequest request) {
        return bookingService.createBooking(request);
    }
    @PostMapping("/confirm/{id}")
    public Booking confirm(@PathVariable Long id) {
        return bookingService.confirmBooking(id);
    }
    
    @GetMapping
    public List<Booking> getAll() {
        return bookingService.getAll();
    }
    
    
    @GetMapping("/{id}")
    public Booking getById(@PathVariable Long id) {
        return bookingService.getById(id);
    }
}