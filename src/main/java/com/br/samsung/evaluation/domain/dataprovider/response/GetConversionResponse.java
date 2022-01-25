package com.br.samsung.evaluation.domain.dataprovider.response;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class GetConversionResponse {
    private LocalDate documentDate;
    private BigDecimal documentValue;
    private BigDecimal valueUSD;
    private BigDecimal valuePEN;
    private BigDecimal valueBRL;
    private String documentNumber;
    private String currencyCode;
    private String currencyDesc;
    private Long currencyId;

}
