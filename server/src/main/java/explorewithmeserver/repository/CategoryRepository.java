package explorewithmeserver.repository;

import explorewithmeserver.model.category.Category;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Andrey Boyarov
 */
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
