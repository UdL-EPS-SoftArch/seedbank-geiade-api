package cat.udl.eps.softarch.demo.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
//import javax.persistence.Table;

@Entity
//@Table(name = "Donation")
@Data
public class DonationEntity /*extends Batch*/{
    //private Donor donor;
    //private Batch batch;
    @Id
    private long id;
}
