package com.dro.pfg.pfgproductcost.model;

import lombok.*;
import lombok.extern.jackson.Jacksonized;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Jacksonized
@ToString
public class PriceDto {

    private String id;
    private float price;
    private float deliveryCost;
    private String currency;

}
