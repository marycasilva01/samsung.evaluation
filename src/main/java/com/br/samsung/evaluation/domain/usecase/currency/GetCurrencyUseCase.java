package com.br.samsung.evaluation.domain.usecase.currency;

import com.br.samsung.evaluation.domain.dataprovider.response.GetCurrencyResponse;

import java.util.List;

@FunctionalInterface
interface GetCurrencyUseCase {

    List<GetCurrencyResponse> execute();
}
