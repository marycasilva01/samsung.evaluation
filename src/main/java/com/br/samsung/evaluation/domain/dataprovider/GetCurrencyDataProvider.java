package com.br.samsung.evaluation.domain.dataprovider;

import com.br.samsung.evaluation.domain.dataprovider.response.GetCurrencyResponse;

import java.util.List;

public interface GetCurrencyDataProvider {
    List<GetCurrencyResponse> execute();
}
