package explorewithmeserver.repository;

import explorewithmeserver.model.event.Event;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Andrey Boyarov
 */

@Repository
@RequiredArgsConstructor
public class CriteriaRepository {

    private final EntityManager entityManager;

    public List<Event> searchEventsOnlyAvailable(String text,
                                          List<Long> catId,
                                          Boolean paid,
                                          LocalDateTime rangeStart,
                                          LocalDateTime rangeEnd,
                                          Integer from,
                                          Integer size){

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Event> query = builder.createQuery(Event.class);
        Root<Event> root = query.from(Event.class);
        List<Predicate> predicates = new ArrayList<>();


        if (Objects.nonNull(text)) {
            predicates.add(
                    builder.or(
                            builder.like(builder.upper(root.get("annotation")), "%" + text.toUpperCase() + "%"),
                            builder.like(builder.upper(root.get("description")), "%" + text.toUpperCase() + "%"))
            );
        }
        if (Objects.nonNull(catId) && !catId.isEmpty()) {
            predicates.add(builder.in(root.get("category").get("id")).value(catId));
        }
        if (Objects.nonNull(paid)) {
            predicates.add(builder.equal(root.get("paid"), paid));
        }
        if (Objects.nonNull(rangeStart)) {
            predicates.add(builder.greaterThan(root.get("eventDate"), rangeStart));
        }
        if (Objects.nonNull(rangeEnd)) {
            predicates.add(builder.lessThan(root.get("eventDate"), rangeEnd));
        }


        return entityManager.createQuery(query.select(root).where(builder.and(predicates.toArray(predicates.toArray(new Predicate[]{})))))
                .setFirstResult(from)
                .setMaxResults(size)
                .getResultList();
    }
}