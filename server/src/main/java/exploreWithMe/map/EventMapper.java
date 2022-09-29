package exploreWithMe.map;

import exploreWithMe.model.category.Category;
import exploreWithMe.model.event.*;
import exploreWithMe.model.user.User;
import exploreWithMe.model.user.UserShortDto;
import exploreWithMe.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Andrey Boyarov
 */
@Component
@RequiredArgsConstructor
public class EventMapper {

    private final ModelMapper mapper;

    private final UserMapper userMapper;

    private final CategoryRepository categoryRepository;

    public EventDto mapToEventDto(Event event){
        return mapper.map(event, EventDto.class);
    }

    private final Converter<String, LocalDateTime> convertDate = src -> src
            .getSource() == null ? null : mapToDate(src.getSource());

    private final Converter<Category, Long> convertToCategoryId = src -> src
            .getSource() == null ? null : src.getSource().getId();

    Converter<Long, Category> convertToCategory = src -> src
            .getSource() == null ? null : findCategory(src.getSource());

    Converter<User, UserShortDto> converterUser = src -> src
            .getSource() == null ? null : mapUser(src.getSource());

    public Event mapToEvent(NewEventDto eventDto){
        mapper.createTypeMap(NewEventDto.class, Event.class)
                .addMappings(m -> m.using(convertDate)
                        .map(NewEventDto::getEventDate, Event::setEventDate));
        return mapper.map(eventDto, Event.class);
    }

    public Event mapToEvent(EventUpdateDto eventUpdateDto){
        mapper.createTypeMap(EventUpdateDto.class, Event.class)
                .addMappings(m -> m.using(convertDate)
                        .map(EventUpdateDto::getEventDate, Event::setEventDate))
                .addMappings(m -> m.using(convertToCategory)
                        .map(EventUpdateDto::getCategory, Event::setCategory));
        return mapper.map(eventUpdateDto, Event.class);
    }

    public EventShortDto mapToEventShortDto(Event event){
        mapper.createTypeMap(Event.class, EventShortDto.class)
                .addMappings(m -> m.using(converterUser)
                        .map(Event::getInitiator, EventShortDto::setInitiator));
        return mapper.map(event, EventShortDto.class);
    }

    public LocalDateTime mapToDate(String date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.parse(date, formatter);
    }

    private UserShortDto mapUser(User user){
        return userMapper.mapToUserShortDto(user);
    }

    private Category findCategory (Long id){
        return categoryRepository.findById(id).orElseThrow();
    }
}
