package com.dro.pfg.pfgproductcost.model;

import lombok.*;
import lombok.extern.jackson.Jacksonized;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Jacksonized
@ToString
public class ProductDto {

    private String id;
    private String name;
    private String description;
    private float price;
    private float deliveryCost;
    private String currency;
    private long availability;

}
