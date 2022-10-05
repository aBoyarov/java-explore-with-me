package exploreWithMeServer.map;

import exploreWithMeServer.model.Location;
import exploreWithMeServer.model.category.Category;
import exploreWithMeServer.model.event.*;
import exploreWithMeServer.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

/**
 * @author Andrey Boyarov
 */
@Component
@RequiredArgsConstructor
public class EventMapper {

    private final ModelMapper mapper;

    private final CategoryRepository categoryRepository;


    Converter<Long, Category> convertToCategory = src -> src
            .getSource() == null ? null : findCategory(src.getSource());

    Converter<Location, Double> convertLatitude = src -> src
            .getSource() == null ? null : src.getSource().getLat();

    Converter<Location, Double> convertLongitude = src -> src
            .getSource() == null ? null : src.getSource().getLon();

    Converter<Long, Long> convertId = src -> src
            .getSource() == null ? null : src.getSource();


    public EventDto mapToEventDto(Event event) {
        EventDto eventDto = mapper.map(event, EventDto.class);
        eventDto.setLocation(Location.builder()
                .lat(event.getLatitude())
                .lon(event.getLongitude())
                .build());
        return eventDto;
    }

    public Event mapToEvent(NewEventDto newEventDto) {
        mapper.typeMap(NewEventDto.class, Event.class)
                .addMappings(m -> m.using(convertToCategory)
                        .map(NewEventDto::getCategory, Event::setCategory))
                .addMappings(lat -> lat.using(convertLatitude)
                        .map(NewEventDto::getLocation, Event::setLatitude))
                .addMappings(lon -> lon.using(convertLongitude)
                        .map(NewEventDto::getLocation, Event::setLongitude));
        return mapper.map(newEventDto, Event.class);
    }


    public void mapToEvent(EventUpdateDto eventUpdateDto, Event event) {
        mapper.typeMap(EventUpdateDto.class, Event.class)
                .addMappings(m -> m.using(convertId)
                        .map(EventUpdateDto::getEventId, Event::setId));
        mapper.map(eventUpdateDto, event);
    }

    public EventShortDto mapToEventShortDto(Event event){
        return mapper.map(event, EventShortDto.class);
    }


    private Category findCategory(Long id) {
        return categoryRepository.findById(id).orElseThrow();
    }
}
