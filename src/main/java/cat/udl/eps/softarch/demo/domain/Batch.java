package cat.udl.eps.softarch.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Batch {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long id;
    @NotNull
    @Min(value = 1, message = "There must be one or more batches")
    private Integer amount;

    @NotNull
    @Min(value = 0, message = "Weight must be positive")
    private Float weight;

    @NotBlank
    @Length(min = 2, max = 30)
    private String location;

    private ZonedDateTime date = ZonedDateTime.now();

    /*
    private List<Seed> seeds;
    */
}
