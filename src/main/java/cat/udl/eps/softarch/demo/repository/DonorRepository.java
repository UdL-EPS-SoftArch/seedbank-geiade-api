package cat.udl.eps.softarch.demo.repository;

import cat.udl.eps.softarch.demo.domain.Donor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface DonorRepository extends PagingAndSortingRepository<Donor, Integer> {
}
