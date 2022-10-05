package exploreWithMeServer.repository;

import exploreWithMeServer.model.compilation.Compilation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Andrey Boyarov
 */
public interface CompilationRepository extends JpaRepository<Compilation, Long> {

        Page<Compilation> findCompilationsByPinnedIsOrderByIdAsc(Boolean pinned, Pageable pageable);

}
