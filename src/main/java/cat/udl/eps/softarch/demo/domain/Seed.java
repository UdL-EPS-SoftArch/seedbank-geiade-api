package cat.udl.eps.softarch.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class Seed {
    @Id
    private Long id;
    private String scientificName;
    @ElementCollection
    private List<String> commonNames;
    @ManyToMany
    private List<Seed> beneficialFor;

}
