package exploreWithMeServer.repository;

import exploreWithMeServer.model.category.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Andrey Boyarov
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}