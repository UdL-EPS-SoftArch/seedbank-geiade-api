package cat.udl.eps.softarch.demo.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public enum DonationStatus {
    PENDING("Pending"), ACCEPTED("Accepted"), REFUSED("Refused");

    private String description;
}
