package lobiko.com.repositories;

import lobiko.com.entities.Hopital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface HopitalRepository extends JpaRepository<Hopital, Long> {
}
