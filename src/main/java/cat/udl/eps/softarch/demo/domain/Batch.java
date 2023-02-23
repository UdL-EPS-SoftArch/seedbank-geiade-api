package cat.udl.eps.softarch.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Batch {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long id;
    private int amount = -1;
    private Long latitude;
    private Long longitude;
    private ZonedDateTime date;

    /*
    @ManyToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "batch_id")
    private List<Seed> seeds;
    */
}
