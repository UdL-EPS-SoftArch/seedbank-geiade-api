package cat.udl.eps.softarch.demo.domain;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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
    @JsonIdentityReference(alwaysAsId = true)
    private Propagator by;

    @OneToOne
    @Nullable
    @JsonIdentityReference(alwaysAsId = true)
    private Take fulfilledBy;


}
