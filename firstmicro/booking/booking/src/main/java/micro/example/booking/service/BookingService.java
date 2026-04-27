package micro.example.booking.service;

import micro.example.booking.dto.BookingRequest;
import micro.example.booking.entity.Booking;
import micro.example.booking.entity.BookingStatus;
import micro.example.booking.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private RestTemplate restTemplate;

    public Booking createBooking(BookingRequest request) {

        // 1. CALL SEAT SERVICE → LOCK SEATS
        String seatServiceUrl =
                "http://localhost:8082/seats/lock";

        restTemplate.postForObject(
                seatServiceUrl,
                request,
                String.class
        );

        // 2. CREATE BOOKING
        Booking booking = new Booking();
        booking.setUserId(request.getUserId());
        booking.setShowId(request.getShowId());
        booking.setSeatIds(request.getSeatIds());
        booking.setStatus(BookingStatus.PENDING);
        booking.setBookingTime(LocalDateTime.now());

        return bookingRepository.save(booking);
    }

    // CONFIRM BOOKING
    public Booking confirmBooking(Long bookingId) {

        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        booking.setStatus(BookingStatus.CONFIRMED);

        // CALL SEAT SERVICE → CONFIRM SEATS
        String seatServiceUrl =
                "http://localhost:8082/seats/confirm";

        restTemplate.postForObject(
                seatServiceUrl,
                booking.getSeatIds(),
                String.class
        );

        return bookingRepository.save(booking);
    }
}