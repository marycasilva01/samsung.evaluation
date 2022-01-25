package com.br.samsung.evaluation.domain.dataprovider;

import com.br.samsung.evaluation.domain.dataprovider.response.GetConversionResponse;

import java.time.LocalDate;
import java.util.List;

public interface GetConversionDataProvider {

    List<GetConversionResponse> execute(String documentNumber, String  currencyCode,
                                        LocalDate startDate, LocalDate endDate);
}
