package micro.example.seat.schedular;
import micro.example.seat.service.SeatService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SeatScheduler {

    private final SeatService seatService;

    public SeatScheduler(SeatService seatService) {
        this.seatService = seatService;
    }

    @Scheduled(fixedRate = 60000) // every 1 minute
    public void releaseExpiredSeats() {
        seatService.releaseExpiredLocks();
    }
}