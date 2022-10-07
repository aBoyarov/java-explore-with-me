package exploreWithMeStats.service;

import exploreWithMeStats.model.NewEndpointHit;
import exploreWithMeStats.model.ViewStatsDto;


import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Andrey Boyarov
 */
public interface StatsService {
    List<ViewStatsDto> getStats(LocalDateTime start, LocalDateTime end, List<String> uris, Boolean unique);

    void save(NewEndpointHit newEndpointHit);
}
