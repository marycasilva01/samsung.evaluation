package com.br.samsung.evaluation.domain.dataprovider;

import com.br.samsung.evaluation.dataprovider.client.model.QuotationDTO;

import java.time.LocalDate;
import java.util.List;

public interface GetQuotationDataProvider {
     List<QuotationDTO> execute(String currencyCode);
}
