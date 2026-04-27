package micro.example.seat.dto;

import java.util.List;
public class ConfirmSeatRequest {

    private List<Long> seatIds;

    public List<Long> getSeatIds() {
        return seatIds;
    }

    public void setSeatIds(List<Long> seatIds) {
        this.seatIds = seatIds;
    }
}