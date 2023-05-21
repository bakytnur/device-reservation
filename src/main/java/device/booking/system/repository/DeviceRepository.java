package device.booking.system.repository;

import device.booking.system.entity.Brand;
import device.booking.system.entity.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Integer> {
    List<Device> findAll();
    Optional<Device> findDeviceById(int deviceId);
}