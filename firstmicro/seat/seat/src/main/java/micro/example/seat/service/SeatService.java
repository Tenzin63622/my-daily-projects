package micro.example.seat.service;

import micro.example.seat.entity.Seat;
import micro.example.seat.entity.SeatStatus;
import micro.example.seat.repository.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SeatService {

    @Autowired
    private SeatRepository seatRepository;

    // Create seats for a show
    public Seat addSeat(Seat seat) {
        seat.setStatus(SeatStatus.AVAILABLE);
        return seatRepository.save(seat);
    }

    // Get seats by show
    public List<Seat> getSeatsByShow(Long showId) {
        return seatRepository.findByShowId(showId);
    }

    // LOCK SEATS (MOST IMPORTANT 🔥)
    public void lockSeats(List<Long> seatIds, Long userId) {

        List<Seat> seats = seatRepository.findAllById(seatIds);

        for (Seat seat : seats) {

            if (seat.getStatus() != SeatStatus.AVAILABLE) {
                throw new RuntimeException("Seat already booked or locked");
            }

            seat.setStatus(SeatStatus.LOCKED);
            seat.setLockedByUserId(userId);
            seat.setLockTime(LocalDateTime.now());
        }

        seatRepository.saveAll(seats);
    }

    // CONFIRM BOOKING
    public void confirmSeats(List<Long> seatIds) {

        List<Seat> seats = seatRepository.findAllById(seatIds);

        for (Seat seat : seats) {
            seat.setStatus(SeatStatus.BOOKED);
        }

        seatRepository.saveAll(seats);
    }

    // RELEASE EXPIRED LOCKS
    public void releaseExpiredLocks() {

        List<Seat> lockedSeats = seatRepository.findByStatus(SeatStatus.LOCKED);

        for (Seat seat : lockedSeats) {

            if (seat.getLockTime() != null &&
                seat.getLockTime().plusMinutes(5).isBefore(LocalDateTime.now())) {

                seat.setStatus(SeatStatus.AVAILABLE);
                seat.setLockedByUserId(null);
                seat.setLockTime(null);
            }
        }

        seatRepository.saveAll(lockedSeats);
    }
}