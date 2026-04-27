package micro.example.seat.dto;

import java.util.List;

public class LockSeatRequest {

    private List<Long> seatIds;
    private Long userId;

    public List<Long> getSeatIds() {
        return seatIds;
    }

    public void setSeatIds(List<Long> seatIds) {
        this.seatIds = seatIds;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}