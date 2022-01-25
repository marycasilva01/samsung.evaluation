package com.br.samsung.evaluation.dataprovider;

import com.br.samsung.evaluation.dataprovider.client.EvaluationAPI;
import com.br.samsung.evaluation.domain.dataprovider.GetCurrencyDataProvider;
import com.br.samsung.evaluation.domain.dataprovider.response.GetCurrencyResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class GetCurrencyDataProviderImpl implements GetCurrencyDataProvider {

    private final EvaluationAPI api;

    @Override
    public List<GetCurrencyResponse> execute(){

       return api.getCurrency().stream().map(GetCurrencyResponse::new).collect(Collectors.toList());
    }
}
