package com.br.samsung.evaluation.domain.dataprovider;

import com.br.samsung.evaluation.dataprovider.client.model.DocsDTO;

import java.time.LocalDate;
import java.util.List;

public interface GetDocumentsDataProvider {

    List<DocsDTO> execute(String documentNumber, String  currencyCode, LocalDate startDate, LocalDate endDate);
}
