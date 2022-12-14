package explorewithmeserver.repository;

import explorewithmeserver.model.comment.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author Andrey Boyarov
 */
public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query("SELECT c FROM Comment c where c.event.id = ?1 ")
    List<Comment> findAllCommentsByEvent(Long eventId);
}
