package exploreWithMe.repository;

import exploreWithMe.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Andrey Boyarov
 */
public interface LocationRepository extends JpaRepository<Location, Long> {
}
