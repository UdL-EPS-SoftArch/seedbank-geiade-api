package cat.udl.eps.softarch.demo.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)

public class Donor extends User{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
}
