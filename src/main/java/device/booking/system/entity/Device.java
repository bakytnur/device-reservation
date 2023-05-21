package device.booking.system.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Entity
@Table(name = "devices")
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotBlank(message = "Device name is mandatory")
    private String name;

    @ManyToOne
    @JoinColumn(name="brand_id", referencedColumnName="id")
    private Brand brand;

    private String technology;
    @Column(length = 1000)
    private String twoGBands;
    @Column(length = 1000)
    private String threeGBands;
    @Column(length = 1000)
    private String fourGBands;
}
