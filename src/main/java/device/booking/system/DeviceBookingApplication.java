package device.booking.system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class DeviceBookingApplication {

	public static void main(String[] args) {
		SpringApplication.run(DeviceBookingApplication.class, args);
	}

}
