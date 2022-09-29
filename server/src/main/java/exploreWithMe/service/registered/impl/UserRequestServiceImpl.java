package exploreWithMe.service.registered.impl;

import exploreWithMe.model.request.Request;
import exploreWithMe.model.request.RequestDto;
import exploreWithMe.model.request.RequestState;
import exploreWithMe.repository.EventRepository;
import exploreWithMe.repository.RequestRepository;
import exploreWithMe.repository.UserRepository;
import exploreWithMe.service.registered.UserRequestService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Andrey Boyarov
 */
@Service
@RequiredArgsConstructor
public class UserRequestServiceImpl implements UserRequestService {

    private final RequestRepository requestRepository;
    private final UserRepository userRepository;

    private final EventRepository eventRepository;

    private final ModelMapper mapper;
    @Override
    public List<RequestDto> getRequests(Long userId) {
        List<Request> requests = requestRepository.findAllRequestsByUserId(userId);
        return requests.stream()
                .map(this::toRequestDto)
                .collect(Collectors.toList());
    }

    @Override
    public RequestDto createRequest(Long userId, Long eventId) {
        Request request = new Request();
        request.setRequester(userRepository.findById(userId).orElseThrow());
        request.setEvent(eventRepository.findById(eventId).orElseThrow());
        requestRepository.saveAndFlush(request);
        return toRequestDto(request);
    }

    @Override
    public RequestDto cancelRequest(Long userId, Long requestId) {
        Request request = requestRepository.findRequestsByReqId(requestId, userId);
        request.setStatus(RequestState.CANCELED);
        requestRepository.saveAndFlush(request);
        return toRequestDto(request);
    }

    private RequestDto toRequestDto(Request request){
       return mapper.map(request, RequestDto.class);
    }
}
