package com.br.samsung.evaluation.dataprovider.client.model;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class DocsDTO {
    private Long documentId;
    private String documentNumber;
    private String notaFiscal;
    private LocalDate documentDate;
    private BigDecimal documentValue;
    private String currencyCode;
}
