package cat.udl.eps.softarch.demo.domain;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
public class Batch {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private int amount;
    private long latitude;
    private long longitude;
    private ZonedDateTime date;

    /*
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "batch_id")
    private List<Seed> seeds;
    */
}
