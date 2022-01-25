package com.br.samsung.evaluation.dataprovider.client.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class QuotationDTO {

    private Long quotationId;
    private String fromCurrencyCode;
    private String toCurrencyCode;
    private String cotacao;
    private LocalDate dataHoraCotacao;
}
