package cat.udl.eps.softarch.demo.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;


@Entity
@Data
@EqualsAndHashCode(callSuper = false)
public class Admin extends User {

}
