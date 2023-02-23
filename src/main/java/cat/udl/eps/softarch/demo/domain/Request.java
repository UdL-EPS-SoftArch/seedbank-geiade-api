package cat.udl.eps.softarch.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Request extends Batch {
    @Id
    private Long id;

    private ZonedDateTime lastUpdate;

    @OneToOne
    private Take fulfilledBy;
}
