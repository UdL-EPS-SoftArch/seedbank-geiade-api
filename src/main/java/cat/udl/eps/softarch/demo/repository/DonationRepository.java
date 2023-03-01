package cat.udl.eps.softarch.demo.repository;

import cat.udl.eps.softarch.demo.domain.Donation;
import cat.udl.eps.softarch.demo.domain.Donor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface DonationRepository extends PagingAndSortingRepository<Donation, Long> {
    List<Donor> findByBy(Donor by);
}
