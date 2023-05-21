package device.booking.system.model.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeviceResponse {
    private int deviceId;
    private String deviceName;
    private String brandName;
    private String technology;
    private String twoGBands;
    private String threeGBands;
    private String fourGBands;
}
