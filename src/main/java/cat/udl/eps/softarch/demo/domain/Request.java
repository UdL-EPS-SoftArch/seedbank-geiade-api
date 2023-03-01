package cat.udl.eps.softarch.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Request extends Batch {

    private ZonedDateTime lastUpdate;

    @NotNull
    @ManyToOne
    private Propagator by;

    @OneToOne
    @Nullable
    private Take fulfilledBy;


}
