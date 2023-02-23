package cat.udl.eps.softarch.demo.repository;

import cat.udl.eps.softarch.demo.domain.Propagator;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/*

@RepositoryRestResource
public interface PropagatorRepository extends PagingAndSortingRepository<Propagator, Long> {
         Interface provides automatically, as defined in https://docs.spring.io/spring-data/commons/docs/current/api/org/springframework/data/repository/PagingAndSortingRepository.html
         * count, delete, deleteAll, deleteById, existsById, findAll, findAllById, findById, save, saveAll
         *
         * Additional methods following the syntax defined in
         * https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods.query-creation


        List<Propagator> findByUsernameContaining(@Param("text") String text);
        }
}

* */

