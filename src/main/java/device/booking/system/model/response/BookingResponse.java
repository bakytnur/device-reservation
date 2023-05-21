package device.booking.system.model.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class BookingResponse {
    private long bookingId;
    private String whoBooked;
    private LocalDateTime bookingDate;
    private LocalDateTime returnDate;
}
