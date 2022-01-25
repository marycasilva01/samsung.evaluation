package com.br.samsung.evaluation.domain.usecase.conversion;

import com.br.samsung.evaluation.domain.dataprovider.response.GetConversionResponse;

import java.util.List;

@FunctionalInterface
interface GetConversionUseCase {

    List<GetConversionResponse> execute(String documentNumber, String currencyCode, String startDate, String endDate);
}
