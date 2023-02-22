package cat.udl.eps.softarch.demo.domain;

import cat.udl.eps.softarch.demo.utils.DonationStatus;
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
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @NotNull
    private DonationStatus donationStatus = DonationStatus.PENDING;
}
