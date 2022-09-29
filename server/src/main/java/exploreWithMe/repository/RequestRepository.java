package exploreWithMe.repository;

import exploreWithMe.model.request.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author Andrey Boyarov
 */
public interface RequestRepository extends JpaRepository<Request, Long> {

    List<Request> findRequestsByRequesterId(Long userId);

    @Query("from Request r where r.id = ?1" +
            "and r.requester.id = ?2")
    Request findRequestsByReqId(Long reqId, Long userId);

    @Query("from Request r where r.id = ?1 " +
            "and r.event.id = ?2 " +
            "and r.requester.id = ?3")
    Request findRequestByUserId(Long reqId, Long eventId, Long userId);

    @Query("from Request r where r.requester.id = ?1")
    List<Request> findAllRequestsByUserId(Long userId);
}
