package device.booking.system.repository;

import device.booking.system.entity.UserDeviceBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserDeviceBookingRepository extends JpaRepository<UserDeviceBooking, Integer> {
    Optional<UserDeviceBooking> findUserDeviceBookingByDeviceIdOrderByIdDesc(int deviceId);

    @Query(
            value = "SELECT booking.* FROM user_device_booking booking INNER JOIN "
                    + " (SELECT MAX(id) id, device_id FROM user_device_booking WHERE id IN (:deviceIdList)"
                    + " GROUP BY device_id) as latest ON latest.id = booking.id",
            nativeQuery = true
    )
    List<UserDeviceBooking> findByDeviceIdInOrderByIdDesc(@Param("deviceIdList") List<Integer> deviceIdList);
}
