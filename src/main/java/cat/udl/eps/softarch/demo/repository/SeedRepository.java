package cat.udl.eps.softarch.demo.repository;

import cat.udl.eps.softarch.demo.domain.Seed;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface SeedRepository extends PagingAndSortingRepository<Seed, Long> {
}
