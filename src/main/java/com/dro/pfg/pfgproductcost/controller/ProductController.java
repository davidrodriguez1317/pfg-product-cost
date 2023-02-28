package com.dro.pfg.pfgproductcost.controller;

import com.dro.pfg.pfgproductcost.model.ProductDto;
import com.dro.pfg.pfgproductcost.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/{id}")
    public ProductDto getProduct(@PathVariable(value="id") String id) {
        return productService.getProductById(id);
    }

}
