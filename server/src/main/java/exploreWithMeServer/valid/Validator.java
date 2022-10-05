package exploreWithMeServer.valid;

import exploreWithMeServer.exception.NotFoundException;
import exploreWithMeServer.model.category.Category;
import exploreWithMeServer.model.compilation.Compilation;
import exploreWithMeServer.model.event.Event;
import exploreWithMeServer.model.user.User;
import exploreWithMeServer.repository.CategoryRepository;
import exploreWithMeServer.repository.CompilationRepository;
import exploreWithMeServer.repository.EventRepository;
import exploreWithMeServer.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author Andrey Boyarov
 */
@Component
@RequiredArgsConstructor
public class Validator {

    private final CompilationRepository compilationRepository;
    private final EventRepository eventRepository;

    private final CategoryRepository categoryRepository;

    private final UserRepository userRepository;

    public Compilation validCompilation(Long id) throws NotFoundException {
        return compilationRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(
                        String.format("Compilation with id=%d was not found.", id)));
    }

    public Event validEvent(Long id) throws NotFoundException {
        return eventRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(
                        String.format("Event with id=%d was not found.", id)));
    }

    public Category validCategory(Long id) throws NotFoundException {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(
                        String.format("Category with id=%d was not found.", id)));
    }

    public User validUser(Long id) throws NotFoundException {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(
                        String.format("User with id=%d was not found.", id)));
    }
}
