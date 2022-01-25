package com.br.samsung.evaluation.domain.usecase.currency;

import com.br.samsung.evaluation.domain.dataprovider.GetCurrencyDataProvider;
import com.br.samsung.evaluation.domain.dataprovider.response.GetCurrencyResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetCurrencyUseCaseImpl implements GetCurrencyUseCase{

    private final GetCurrencyDataProvider getCurrencyDataProvider;

    public List<GetCurrencyResponse> execute(){
         return getCurrencyDataProvider.execute();
    }

}
