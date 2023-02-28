package com.dro.pfg.pfgproductcost.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.extern.jackson.Jacksonized;

import java.time.Instant;
import java.util.List;


@RequiredArgsConstructor
@AllArgsConstructor
@Jacksonized
@Builder
public class ApiError {

    private final List<String> errors;
    @Builder.Default
    private Instant instant = Instant.now();

}
