package exploreWithMeStats.service;

import exploreWithMeStats.model.EndpointHit;
import exploreWithMeStats.model.NewEndpointHit;
import exploreWithMeStats.model.ViewStats;

import exploreWithMeStats.repository.StatsRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Andrey Boyarov
 */
@Service
@RequiredArgsConstructor
public class StatsServiceImpl implements StatsService {

    private final StatsRepository repository;

    private final ModelMapper mapper;

    @Override
    public void save(NewEndpointHit newEndpointHit) {
        EndpointHit endpointHit = mapper.map(newEndpointHit, EndpointHit.class);
        endpointHit.setTime(LocalDateTime.now());
        repository.save(endpointHit);
    }

    @Override
    public List<ViewStats> getStats(LocalDateTime start,
                                    LocalDateTime end,
                                    List<String> uris,
                                    Boolean unique) {
        if (unique) {
            return repository.getUniqueViews(start, end, uris);
        } else {
            return repository.getViews(start, end, uris);
        }
    }
}
