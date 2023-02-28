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

    public ProductDto getProductById(String id) {

        PriceDto price = priceService.getPriceFromId(id);


        return ProductDto.builder()
                .deliveryCost(price.getDeliveryCost())
                .id(id)
                .currency(price.getCurrency())
                .price(price.getPrice())
                .build();

    }
}
