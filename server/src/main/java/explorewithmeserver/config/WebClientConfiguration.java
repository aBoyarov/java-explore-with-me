package explorewithmeserver.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * @author Andrey Boyarov
 */
@Configuration
@RequiredArgsConstructor
public class WebClientConfiguration {
    @Value("${ewm-stats.url}")
    private String baseUrl;

    @Bean
    public WebClient webClientWithTimeout() {
        return WebClient.builder()
                .baseUrl(baseUrl)
                .build();
    }
}
