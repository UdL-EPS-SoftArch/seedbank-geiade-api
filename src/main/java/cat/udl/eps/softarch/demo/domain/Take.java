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
public class Take extends Batch {
    @Id
    private Long id;
    private ZonedDateTime lastUpdate;
    @OneToOne
    private Request request;
//    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    @Column(nullable = false)
//    private Propagator propagator;

}
