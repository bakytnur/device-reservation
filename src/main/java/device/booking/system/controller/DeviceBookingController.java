package device.booking.system.controller;

import device.booking.system.model.response.DeviceBookingResponse;
import device.booking.system.model.response.DeviceResponse;
import device.booking.system.service.DeviceBookingService;
import device.booking.system.util.AuthenticationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/booking")
public class DeviceBookingController {
    @Autowired
    private AuthenticationUtil authenticationUtil;
    @Autowired
    private DeviceBookingService deviceBookingService;

    @GetMapping("/devices")
    public List<DeviceResponse> getAllDevices() {
        return deviceBookingService.getAllDevices();
    }

    @GetMapping("/devices/booking/{device_id}")
    public DeviceBookingResponse getDeviceBookingState(@PathVariable("device_id") int deviceId) {
        return deviceBookingService.getDeviceBookingState(deviceId);
    }
    @GetMapping("/devices/booking")
    public List<DeviceBookingResponse> getDeviceBookingStates() {
        return deviceBookingService.getDeviceBookingStates();
    }

    @PostMapping("/device")
    public DeviceBookingResponse bookDevice(@RequestHeader(value = "Authorization") String authorization,
                                                  @RequestBody int deviceId) {
        long userId = authenticationUtil.validateTokenAndGetUserId(authorization);
        return deviceBookingService.bookDevice(userId, deviceId);
    }

    @PatchMapping("/device")
    public DeviceBookingResponse returnDevice(@RequestHeader(value = "Authorization") String authorization,
                                            @RequestBody int deviceId) {
        long userId = authenticationUtil.validateTokenAndGetUserId(authorization);
        return deviceBookingService.returnDevice(userId, deviceId);
    }
}
