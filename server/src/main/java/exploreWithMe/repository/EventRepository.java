package exploreWithMe.repository;

import exploreWithMe.model.event.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Andrey Boyarov
 */
public interface EventRepository extends JpaRepository<Event, Long> {

    @Query("from Event e " +
            "where (upper(e.annotation) like upper(concat('%', ?1, '%')) " +
            "or upper(e.description) like upper(concat('%', ?1, '%'))) " +
            "and e.category.id in (?2) " +
            "and e.paid = ?3 " +
            "and e.eventDate between (?4) and (?5) " +
            "and e.confirmedRequests < e.participantLimit " +
            "order by ?6")
    Page<Event> getEventsOnlyAvailable(String text,
                                       List<Long> catId,
                                       Boolean paid,
                                       LocalDateTime rangeStart,
                                       LocalDateTime rangeEnd,
                                       String sort,
                                       Pageable pageable);

    @Query("from Event e " +
            "where (upper(e.annotation) like upper(concat('%', ?1, '%')) " +
            "or upper(e.description) like upper(concat('%', ?1, '%'))) " +
            "and e.category.id in (?2) " +
            "and e.paid = ?3 " +
            "and e.eventDate between (?4) and (?5) " +
            "order by ?6")
    Page<Event> getEvents(String text,
                          List<Long> catId,
                          Boolean paid,
                          LocalDateTime rangeStart,
                          LocalDateTime rangeEnd,
                          String sort,
                          Pageable pageable);

    @Query("from Event e " +
            "where e.initiator.id in (?1)" +
            "and e.state in (?2) " +
            "and e.category in (?3) " +
            "and e.eventDate between (?4) and (?5)")
    Page<Event> getAllEvents(List<Long> users,
                             List<String> states,
                             List<Long> categories,
                             LocalDateTime rangeStart,
                             LocalDateTime rangeEnd,
                             Pageable pageable);


    Page<Event> findEventsByInitiatorId(Long userId, Pageable pageable);

    Event findEventByIdAndInitiatorId(Long eventId, Long userId);
}
