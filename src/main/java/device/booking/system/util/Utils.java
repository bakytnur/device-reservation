package device.booking.system.util;

import device.booking.system.entity.Device;
import device.booking.system.entity.UserDeviceBooking;
import device.booking.system.model.DeviceState;
import device.booking.system.model.response.BookingResponse;
import device.booking.system.model.response.DeviceBookingResponse;
import device.booking.system.model.response.DeviceResponse;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Base64;

public class Utils {

    public static DeviceBookingResponse mapBookingInfoToBookingResponse(DeviceResponse deviceInfo, UserDeviceBooking bookingInfo) {
        DeviceBookingResponse deviceBookingResponse = new DeviceBookingResponse();
        deviceBookingResponse.setDeviceInfo(deviceInfo);
        deviceBookingResponse.setIsAvailable(DeviceState.YES);
        deviceBookingResponse.setIsAvailable(isDeviceAvailable(bookingInfo));

        if (bookingInfo == null) {
            return deviceBookingResponse;
        }

        BookingResponse bookingResponse = new BookingResponse();
        bookingResponse.setBookingId(bookingInfo.getId());
        bookingResponse.setWhoBooked(bookingInfo.getUser().getEmail());
        bookingResponse.setBookingDate(LocalDateTime.ofInstant(bookingInfo.getBookingDate().toInstant(),
                ZoneId.systemDefault()));
        if (bookingResponse.getReturnDate() != null) {
            bookingResponse.setReturnDate(LocalDateTime.ofInstant(bookingInfo.getReturnDate().toInstant(),
                    ZoneId.systemDefault()));
        }

        deviceBookingResponse.setBookingInfo(bookingResponse);

        return deviceBookingResponse;
    }

    /**
     * Returns if device is available
     * Device is available when there is no booking information in booking records or last booking is returned
     * @param bookingInfo
     * @return
     */
    private static DeviceState isDeviceAvailable(UserDeviceBooking bookingInfo) {
        return (bookingInfo == null || bookingInfo.getReturnDate() != null) ? DeviceState.YES : DeviceState.NO;
    }

    public static DeviceResponse mapDeviceInfoToDeviceResponse(Device device) {
        DeviceResponse deviceResponse = new DeviceResponse();
        deviceResponse.setDeviceId(device.getId());
        deviceResponse.setDeviceName(device.getName());
        deviceResponse.setBrandName(device.getBrand().getName());
        deviceResponse.setTechnology(device.getTechnology());
        deviceResponse.setTwoGBands(device.getTwoGBands());
        deviceResponse.setThreeGBands(device.getThreeGBands());
        deviceResponse.setFourGBands(device.getFourGBands());
        return deviceResponse;
    }
}
