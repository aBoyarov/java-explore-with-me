package exploreWithMeStats.service;

import exploreWithMeStats.map.Mapper;
import exploreWithMeStats.model.EndpointHit;
import exploreWithMeStats.model.NewEndpointHit;
import exploreWithMeStats.model.ViewStatsDto;
import exploreWithMeStats.repository.StatsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Andrey Boyarov
 */
@Service
@RequiredArgsConstructor
public class StatsServiceImpl implements StatsService {

    private final StatsRepository repository;

    private final Mapper mapper;

    @Override
    public void save(NewEndpointHit newEndpointHit) {
        EndpointHit endpointHit = mapper.mapToEndpointHit(newEndpointHit);
        endpointHit.setTime(LocalDateTime.now());
        repository.save(endpointHit);
    }

    @Override
    public List<ViewStatsDto> getStats(LocalDateTime start,
                                       LocalDateTime end,
                                       List<String> uris,
                                       Boolean unique) {
        if (unique) {
            return repository.getUniqueViews(start, end, uris).stream()
                    .map(mapper::mapToViewStatsDto)
                    .collect(Collectors.toList());
        } else {
            return repository.getViews(start, end, uris).stream()
                    .map(mapper::mapToViewStatsDto)
                    .collect(Collectors.toList());
        }
    }
}
