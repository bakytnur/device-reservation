package device.booking.system.service;

import device.booking.system.entity.Device;
import device.booking.system.entity.User;
import device.booking.system.entity.UserDeviceBooking;
import device.booking.system.exception.BadRequestException;
import device.booking.system.exception.OperationNotPermittedException;
import device.booking.system.exception.RecordNotFoundException;
import device.booking.system.model.response.DeviceBookingResponse;
import device.booking.system.model.response.DeviceResponse;
import device.booking.system.repository.DeviceRepository;
import device.booking.system.repository.UserDeviceBookingRepository;
import device.booking.system.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

@Service
public class DeviceBookingService {
    @Autowired
    private UserDeviceBookingRepository userDeviceBookingRepository;
    @Autowired
    private DeviceRepository deviceRepository;


    /**
     * Get a single device information
     */
    private DeviceResponse getDeviceInfo(int deviceId) {
        var deviceOptional = deviceRepository.findDeviceById(deviceId);
        if (deviceOptional.isEmpty()) {
            throw new RecordNotFoundException("Device not found for id " + deviceId);
        }

        var device = deviceOptional.get();
        return Utils.mapDeviceInfoToDeviceResponse(device);
    }


    /**
     * Gets all device information
     */
    public List<DeviceResponse> getAllDevices() {
        var deviceList = deviceRepository.findAll();
        if (CollectionUtils.isEmpty(deviceList)) {
            return Collections.EMPTY_LIST;
        }

        return deviceList.stream().map(Utils::mapDeviceInfoToDeviceResponse).toList();
    }

    /**
     * Books a device
     * @param userId
     * @param deviceId
     * @return
     */
    public DeviceBookingResponse bookDevice(long userId, int deviceId) {
        var deviceInfo = getDeviceInfo(deviceId);

        var deviceBookingOptional = userDeviceBookingRepository.findUserDeviceBookingByDeviceIdOrderByIdDesc(deviceId);
        // device is booked, and has not been returned
        if (deviceBookingOptional.isPresent() && deviceBookingOptional.get().getReturnDate() == null) {
            throw new OperationNotPermittedException("Device is not available");
        }

        UserDeviceBooking userDeviceBooking = new UserDeviceBooking();
        var user = new User();
        user.setId(userId);
        userDeviceBooking.setUser(user);

        var device = new Device();
        device.setId(deviceId);
        userDeviceBooking.setDevice(device);
        var response = userDeviceBookingRepository.save(userDeviceBooking);
        return Utils.mapBookingInfoToBookingResponse(deviceInfo, response);
    }

    /**
     * Returns a device
     * @param userId
     * @param deviceId
     * @return
     */
    public DeviceBookingResponse returnDevice(long userId, int deviceId) {
        var deviceInfo = getDeviceInfo(deviceId);

        var deviceBookingOptional = userDeviceBookingRepository.findUserDeviceBookingByDeviceIdOrderByIdDesc(deviceId);
        // device is not booked, return not permitted
        if (deviceBookingOptional.isEmpty()) {
            throw new RecordNotFoundException("Device is not booked");
        }

        // device is already returned
        if (deviceBookingOptional.get().getReturnDate() != null) {
            throw new BadRequestException("Device is already returned");
        }

        // device booked by another user
        if (deviceBookingOptional.get().getUser().getId() != userId) {
            throw new BadRequestException("Device was booked by another person");
        }

        var bookingInfo = deviceBookingOptional.get();
        bookingInfo.setReturnDate(Calendar.getInstance());
        userDeviceBookingRepository.save(bookingInfo);

        return Utils.mapBookingInfoToBookingResponse(deviceInfo, bookingInfo);
    }

    /**
     * Returns the booking state of all devices
     * @return
     */
    public List<DeviceBookingResponse> getDeviceBookingStates() {
        var deviceList = deviceRepository.findAll();
        if (CollectionUtils.isEmpty(deviceList)) {
            return Collections.EMPTY_LIST;
        }

        var deviceIdList = deviceList.stream().map(Device::getId).toList();
        List<UserDeviceBooking> bookingList = userDeviceBookingRepository.findByDeviceIdInOrderByIdDesc(deviceIdList);

        HashMap<Integer, UserDeviceBooking> bookingMap = new HashMap<>();

        bookingList.forEach(booking -> {
            if (!bookingMap.containsKey(booking.getDevice().getId())) {
                bookingMap.put(booking.getDevice().getId(), booking);
            }
        });

        return deviceList.stream()
                .map(device -> {
                    var bookingInfo = bookingMap.get(device.getId());
                    var deviceInfo = Utils.mapDeviceInfoToDeviceResponse(device);

                    return Utils.mapBookingInfoToBookingResponse(deviceInfo, bookingInfo);
                }).toList();
    }

    /**
     * Returns the booking state of a single device
     * @param deviceId
     * @return
     */
    public DeviceBookingResponse getDeviceBookingState(int deviceId) {
        var deviceInfo = getDeviceInfo(deviceId);
        var deviceBookingOptional = userDeviceBookingRepository.findUserDeviceBookingByDeviceIdOrderByIdDesc(deviceId);

        return Utils.mapBookingInfoToBookingResponse(deviceInfo, deviceBookingOptional.orElse(null));
    }
}
