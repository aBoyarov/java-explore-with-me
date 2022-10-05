package exploreWithMeServer.controller.all;

import exploreWithMeServer.client.BaseClient;
import exploreWithMeStats.model.NewEndpointHit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.util.DefaultUriBuilderFactory;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;


/**
 * @author Andrey Boyarov
 */
@Service
public class EventClient extends BaseClient {


    @Autowired
    public EventClient(@Value("${ewm-stats.url}") String serverUrl, RestTemplateBuilder builder) {
        super(
                builder
                        .uriTemplateHandler(new DefaultUriBuilderFactory(serverUrl))
                        .build()
        );
    }

    public ResponseEntity<Object> addViews(HttpServletRequest request) {
        String ip = request.getRemoteAddr();
        String uri = request.getRequestURI();
        return post("/hit", NewEndpointHit.builder()
                .app("ExploreWithMe")
                .uri(uri)
                .ip(ip)
                .time(LocalDateTime.now())
                .build());
    }
}
