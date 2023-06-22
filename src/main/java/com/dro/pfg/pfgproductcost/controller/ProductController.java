package com.dro.pfg.pfgproductcost.controller;

import com.dro.pfg.pfgproductcost.model.ProductDto;
import com.dro.pfg.pfgproductcost.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
@Slf4j
public class ProductController {

    private final ProductService productService;

    @GetMapping("/{currency}/{id}")
    public ProductDto getProduct(@PathVariable(value="currency") String currency, @PathVariable(value="id") String id) {

        log.info("Getting product cost for id {} and currency {}", id, currency);
        ProductDto product = productService.getProductById(currency, id);

        log.info("Product information got for id {} and currency {}: {}", id, currency, product);
        return  product;
    }

}
