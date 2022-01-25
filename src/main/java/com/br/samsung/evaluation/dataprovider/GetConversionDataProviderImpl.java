package com.br.samsung.evaluation.dataprovider;

import com.br.samsung.evaluation.dataprovider.client.EvaluationAPI;
import com.br.samsung.evaluation.dataprovider.client.model.DocsDTO;
import com.br.samsung.evaluation.dataprovider.client.model.QuotationDTO;
import com.br.samsung.evaluation.dataprovider.client.model.enums.Currency;
import com.br.samsung.evaluation.domain.dataprovider.GetConversionDataProvider;
import com.br.samsung.evaluation.domain.dataprovider.GetDocumentsDataProvider;
import com.br.samsung.evaluation.domain.dataprovider.GetQuotationDataProvider;
import com.br.samsung.evaluation.domain.dataprovider.response.GetConversionResponse;
import com.br.samsung.evaluation.domain.dataprovider.response.GetCurrencyResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GetConversionDataProviderImpl implements GetConversionDataProvider {

    private final EvaluationAPI api;

    private final GetQuotationDataProvider quotationDataProvider;

    private final GetDocumentsDataProvider documentsDataProvider;

    @Override
    public List<GetConversionResponse> execute(String documentNumber, String  currencyCode,
                                                LocalDate startDate, LocalDate endDate){
        
        var docs = documentsDataProvider.execute(documentNumber, currencyCode, startDate, endDate);
        var quotations = quotationDataProvider.execute(currencyCode);

        return docs.stream().map(d -> this.getConversionResponse(d, quotations)).collect(Collectors.toList());
    }

    private GetConversionResponse getConversionResponse(DocsDTO doc, List<QuotationDTO> quotation) {

        GetConversionResponse dto = new GetConversionResponse();
        var anEnum = api.getCurrency().stream().filter(currency -> currency.getCurrencyCode().equals(doc.getCurrencyCode())).findFirst().get();
        dto.setDocumentValue(doc.getDocumentValue());
        dto.setDocumentDate(doc.getDocumentDate());
        dto.setDocumentNumber(doc.getDocumentNumber());
        dto.setCurrencyCode(anEnum.getCurrencyCode());
        dto.setCurrencyDesc(anEnum.getCurrencyDesc());
        dto.setCurrencyId(anEnum.getCurrencyId());

        if(dto.getCurrencyCode().equals(Currency.USD.getCodigo()))
            dto.setValueUSD(doc.getDocumentValue());
        else if (dto.getCurrencyCode().equals(Currency.BRL.getCodigo()))
            dto.setValueBRL(doc.getDocumentValue());
        else if (dto.getCurrencyCode().equals(Currency.PEN.getCodigo()))
            dto.setValuePEN(doc.getDocumentValue());

        quotation.stream().forEach(q -> {
                    if(q.getFromCurrencyCode().equals(Currency.USD.getCodigo()))
                        dto.setValueUSD(doc.getDocumentValue().multiply(new BigDecimal(q.getCotacao())).setScale(2, RoundingMode.HALF_EVEN));
                    else if (q.getFromCurrencyCode().equals(Currency.BRL.getCodigo()))
                        dto.setValueBRL(doc.getDocumentValue().multiply(new BigDecimal(q.getCotacao())).setScale(2, RoundingMode.HALF_EVEN));
                    else if (q.getFromCurrencyCode().equals(Currency.PEN.getCodigo()))
                        dto.setValuePEN(doc.getDocumentValue().multiply(new BigDecimal(q.getCotacao())).setScale(2, RoundingMode.HALF_EVEN));
                });

        return dto;
    }
}
