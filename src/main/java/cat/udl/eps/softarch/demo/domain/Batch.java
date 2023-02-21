package cat.udl.eps.softarch.demo.domain;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
public class Batch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int amount;
    private String location;
    private LocalDate date;

    /*
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "batch_id")
    private List<Seed> seeds;
    */
}
