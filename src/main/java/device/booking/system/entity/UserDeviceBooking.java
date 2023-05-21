package device.booking.system.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Calendar;

@Getter
@Setter
@Entity
@Table(name = "user_device_booking")
public class UserDeviceBooking {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @ManyToOne
    @JoinColumn(name="user_id", referencedColumnName="id")
    private User user;
    @ManyToOne
    @JoinColumn(name="device_id", referencedColumnName="id")
    private Device device;
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Calendar bookingDate;

    private Calendar returnDate;
}
