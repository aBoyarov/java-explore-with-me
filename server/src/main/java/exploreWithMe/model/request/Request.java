package exploreWithMe.model.request;

import exploreWithMe.model.event.Event;
import exploreWithMe.model.user.User;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author Andrey Boyarov
 */

@Entity
@Table(name = "requests")
@Getter @Setter @ToString
public class Request {
    @Id
    @Column(name = "request_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "created")
    private LocalDateTime created = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "event_id", referencedColumnName = "event_id")
    private Event event;

    @ManyToOne
    @JoinColumn(name = "requester_id", referencedColumnName = "user_id")
    private User requester;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private RequestState status;
}
