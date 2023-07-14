package com.dro.pfg.pfgproductcost.config;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

import java.time.Duration;
import java.util.List;

@Configuration
@Slf4j
public class WebclientConfig {

    @Bean
    public WebClient webClient(WebClient.Builder webClientBuilder) {

        HttpClient httpClient = HttpClient.create()
                .responseTimeout(Duration.ofMillis(5_000L))
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5_000) // millis
                .doOnConnected(connection ->
                        connection
                                .addHandlerLast(new ReadTimeoutHandler(5)) // seconds
                                .addHandlerLast(new WriteTimeoutHandler(5))); //seconds

        return webClientBuilder
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .filter(logRequest())
                .filter(logResponse())
                .build();
    }

    private ExchangeFilterFunction logRequest() {
        return (clientRequest, next) -> {
            log.info("Request: {} {}", clientRequest.method(), clientRequest.url());
            log.info("--- Http Headers: ---");
            clientRequest.headers().forEach(this::logHeader);
            log.info("--- Http Cookies: ---");
            clientRequest.cookies().forEach(this::logHeader);
            return next.exchange(clientRequest);
        };
    }

    private ExchangeFilterFunction logResponse() {
        return ExchangeFilterFunction.ofResponseProcessor(clientResponse -> {
            log.info("Response: {}", clientResponse.statusCode());
            clientResponse.headers().asHttpHeaders()
                    .forEach((name, values) -> values.forEach(value -> log.info("{}={}", name, value)));
            return Mono.just(clientResponse);
        });
    }

    private void logHeader(String name, List<String> values) {
        values.forEach(value -> log.info("{}={}", name, value));
    }
}
