package com.br.samsung.evaluation.domain.model;

import com.br.samsung.evaluation.domain.dataprovider.response.GetConversionResponse;

import java.math.BigDecimal;

public class GetConversionResponseTest {

    public static GetConversionResponse create(){
        var response = new GetConversionResponse();
        response.setDocumentValue(new BigDecimal("10000.19"));
        response.setValueUSD(new BigDecimal("50100.95"));
        response.setValuePEN(new BigDecimal("11900.23"));
        response.setValueBRL(new BigDecimal("10000.19"));
        response.setDocumentNumber(" 800002014");
        response.setCurrencyCode("BRL");
        response.setCurrencyDesc("Real");
        response.setCurrencyId(3L);
        return response;
    }
}
