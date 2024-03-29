package cat.udl.eps.softarch.demo.repository;

import cat.udl.eps.softarch.demo.domain.Propagator;
import cat.udl.eps.softarch.demo.domain.Request;
import cat.udl.eps.softarch.demo.domain.Take;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource
public interface RequestRepository extends PagingAndSortingRepository<Request, Long> {
    Optional<Request> findByFulfilledBy(Take fulfilledBy);
}



