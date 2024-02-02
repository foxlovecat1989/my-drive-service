package userservice.driver.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Driver {
    @Id
    @SequenceGenerator(
            name = "driver_id_sequence",
            sequenceName = "driver_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "driver_id_sequence"
    )
    private Long id;
    private UUID driverId;
    private String name;
    private UUID accountId;
    private DriverState state;
    @CreationTimestamp
    private LocalDateTime createAt;
    @UpdateTimestamp
    private LocalDateTime updatedTime;
    private LocalDateTime lastTimeLogin;
}
