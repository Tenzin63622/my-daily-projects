
package micro.example.booking.service;

import micro.example.booking.dto.BookingRequest;
import micro.example.booking.dto.LockSeatRequest;
import micro.example.booking.dto.ConfirmSeatRequest;
import micro.example.booking.entity.Booking;
import micro.example.booking.entity.BookingStatus;
import micro.example.booking.repository.BookingRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private RestTemplate restTemplate;

    // CREATE BOOKING (LOCK SEATS)
    public Booking createBooking(BookingRequest request) {

        String seatServiceUrl = "http://localhost:8085/seats/lock";

        LockSeatRequest lockRequest = new LockSeatRequest();
        lockRequest.setSeatIds(request.getSeatIds());
        lockRequest.setUserId(request.getUserId());

        restTemplate.postForObject(
                seatServiceUrl,
                lockRequest,
                String.class);

        Booking booking = new Booking();
        booking.setUserId(request.getUserId());
        booking.setShowId(request.getShowId());
        booking.setSeatIds(request.getSeatIds());
        booking.setStatus(BookingStatus.PENDING);
        booking.setBookingTime(LocalDateTime.now());

        return bookingRepository.save(booking);
    }

    // get all booking
    public List<Booking> getAll() {
        return bookingRepository.findAll();
    }

    public Booking getById(Long id) {
        return bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found"));
    }

    // confirm
    public Booking confirmBooking(Long bookingId) {

        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        booking.setStatus(BookingStatus.CONFIRMED);

        // 🔥 FIXED: wrap seatIds into DTO (IMPORTANT)
        ConfirmSeatRequest request = new ConfirmSeatRequest();
        request.setSeatIds(booking.getSeatIds());

        String seatServiceUrl = "http://localhost:8085/seats/confirm";

        restTemplate.postForObject(
                seatServiceUrl,
                request,
                String.class);

        return bookingRepository.save(booking);
    }

    // public void lockSeats(BookingRequest request) {

    //     String seatServiceUrl = "http://localhost:8085/seats/lock";

    //     LockSeatRequest lockRequest = new LockSeatRequest();
    //     lockRequest.setSeatIds(request.getSeatIds());
    //     lockRequest.setUserId(request.getUserId());

    //     restTemplate.postForObject(
    //             seatServiceUrl,
    //             lockRequest,
    //             String.class);
    // }

}