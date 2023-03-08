package cat.udl.eps.softarch.demo.domain;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import lombok.AllArgsConstructor;
import lombok.Data;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Donation extends Batch{
    @NotNull
    @JsonIdentityReference(alwaysAsId = true)
    @ManyToOne
    private Donor by;

    @Nullable
    @JsonIdentityReference(alwaysAsId = true)
    @OneToOne
    private Take takenBy;

}
