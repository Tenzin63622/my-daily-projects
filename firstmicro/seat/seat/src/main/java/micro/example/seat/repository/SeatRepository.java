package micro.example.seat.repository;

import micro.example.seat.entity.Seat;
import micro.example.seat.entity.SeatStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SeatRepository extends JpaRepository<Seat, Long> {

    List<Seat> findByShowId(Long showId);

    List<Seat> findByStatus(SeatStatus status);
}