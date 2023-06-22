package com.dro.pfg.pfgproductcost.service;

import com.dro.pfg.pfgproductcost.model.PriceDto;
import com.dro.pfg.pfgproductcost.model.ProductDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {

    private final PriceService priceService;

    public ProductDto getProductById(String currency, String id) {

        PriceDto price = priceService.getPriceFromId(currency, id);
        log.info("Price received for product with id {} and currency {}: {}", id, currency, price);

        return ProductDto.builder()
                .deliveryCost(price.getDeliveryCost())
                .id(id)
                .currency(currency)
                .price(price.getPrice())
                .build();

    }
}
