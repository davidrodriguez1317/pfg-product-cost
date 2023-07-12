package com.dro.pfg.pfgproductcost.model;

import lombok.*;
import lombok.extern.jackson.Jacksonized;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Jacksonized
@ToString
public class ProductRequestDto {
    private String id;
    private String currency;
}
