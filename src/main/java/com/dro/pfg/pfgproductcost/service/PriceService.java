package com.dro.pfg.pfgproductcost.service;

import com.dro.pfg.pfgproductcost.exception.PriceServiceNotAvailableException;
import com.dro.pfg.pfgproductcost.model.PriceDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class PriceService {

    private final WebClient webClient;

    private final DiscoveryClient discoveryClient;

    public PriceDto getPriceFromId(String currency, String id) {

        URI service = serviceUrl()
                .map(s -> s.resolve(String.format("/price/%s/%s", currency, id)))
                .orElseThrow(PriceServiceNotAvailableException::new);

        log.info("Calling price-calculator. Price service URI: " + service.toString());

        Mono<PriceDto> response = this.webClient
                .get()
                .uri(service)
                .retrieve()
                .bodyToMono(PriceDto.class);

        return response.block();
    }

    public Optional<URI> serviceUrl() {
        return discoveryClient.getInstances("price-calculator")
                .stream()
                .findFirst()
                .map(ServiceInstance::getUri);
    }

}
