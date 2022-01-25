package com.br.samsung.evaluation.dataprovider;

import com.br.samsung.evaluation.dataprovider.client.EvaluationAPI;
import com.br.samsung.evaluation.dataprovider.client.model.QuotationDTO;
import com.br.samsung.evaluation.domain.dataprovider.GetQuotationDataProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GetQuotationDataProviderImpl implements GetQuotationDataProvider {

    private final EvaluationAPI api;

    @Override
    public List<QuotationDTO> execute(String  currencyCode){

        Predicate<QuotationDTO> currencyCodeFilter = quotation-> quotation.getToCurrencyCode().equals(currencyCode);

        return api.getQuotation().stream().filter(currencyCodeFilter).collect(Collectors.toList());
    }
}
