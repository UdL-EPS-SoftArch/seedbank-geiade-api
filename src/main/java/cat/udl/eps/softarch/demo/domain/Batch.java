package cat.udl.eps.softarch.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Batch extends UriEntity<Long>{
    @GeneratedValue(strategy = GenerationType.AUTO)

    @Id
    private Long id;

    @NotNull
    @Min(value = 1, message = "The minimal amount of a batch should be one")
    private Integer amount;

    @NotNull
    @DecimalMin(value = "0", message = "Minim weight should be 0")

    private BigDecimal weight;

    @NotBlank
    @Length(min = 2, max = 30)
    private String location;

    private ZonedDateTime date = ZonedDateTime.now();

}
