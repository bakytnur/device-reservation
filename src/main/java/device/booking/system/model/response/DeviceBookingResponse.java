package device.booking.system.model.response;

import device.booking.system.model.DeviceState;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeviceBookingResponse {
    private DeviceResponse deviceInfo;
    private BookingResponse bookingInfo;
    private DeviceState isAvailable;
}
