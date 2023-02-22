package cat.udl.eps.softarch.demo.repository;

import cat.udl.eps.softarch.demo.domain.Donation;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface DonationRepository extends PagingAndSortingRepository<Donation, Integer> {
}
