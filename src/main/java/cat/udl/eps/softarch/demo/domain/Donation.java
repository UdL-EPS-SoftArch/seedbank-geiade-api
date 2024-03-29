package cat.udl.eps.softarch.demo.domain;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import lombok.AllArgsConstructor;
import lombok.Data;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Donation extends Batch{
    @JsonIdentityReference(alwaysAsId = true)
    @ManyToOne
    @NotNull
    private Donor by;

    @JsonIdentityReference(alwaysAsId = true)
    @OneToOne
    private Take takenBy;

}
