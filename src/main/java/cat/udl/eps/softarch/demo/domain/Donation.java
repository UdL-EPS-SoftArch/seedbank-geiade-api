package cat.udl.eps.softarch.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Donation extends Batch{
    @NotNull
    @ManyToOne
    private Donor donor;
}
