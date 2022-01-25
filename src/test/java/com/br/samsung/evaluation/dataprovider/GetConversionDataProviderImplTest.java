package com.br.samsung.evaluation.dataprovider;

import com.br.samsung.evaluation.dataprovider.client.EvaluationAPI;
import com.br.samsung.evaluation.dataprovider.client.model.CurrencyDTO;
import com.br.samsung.evaluation.dataprovider.client.model.DocsDTO;
import com.br.samsung.evaluation.dataprovider.client.model.QuotationDTO;
import com.br.samsung.evaluation.dataprovider.client.model.enums.Currency;
import com.br.samsung.evaluation.domain.dataprovider.GetConversionDataProvider;
import com.br.samsung.evaluation.domain.dataprovider.GetDocumentsDataProvider;
import com.br.samsung.evaluation.domain.dataprovider.GetQuotationDataProvider;
import static org.mockito.ArgumentMatchers.any;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

@ExtendWith(SpringExtension.class)
 class GetConversionDataProviderImplTest {

    @Mock
    private EvaluationAPI api;

    @Mock
    private GetQuotationDataProvider quotationDataProvider;

    @Mock
    private GetDocumentsDataProvider documentsDataProvider;
    
    @InjectMocks
    private GetConversionDataProviderImpl getConversionDataProviderImpl;

    private String documentNumber = "800002014";
    private String currencyCode = "BRL";
    private LocalDate startDate = LocalDate.now();
    private LocalDate endDate = LocalDate.now();

    @Test
    void test_execute_success(){

        var doc = new DocsDTO();
        doc.setDocumentDate(LocalDate.now());
        doc.setCurrencyCode(Currency.BRL.getCodigo());
        doc.setDocumentNumber(Currency.BRL.getCodigo());
        doc.setDocumentValue(BigDecimal.ONE);

        var quotation = new QuotationDTO();
        quotation.setCotacao("0.2");
        quotation.setFromCurrencyCode(Currency.USD.getCodigo());

        var currency = new CurrencyDTO();
        currency.setCurrencyId(Currency.BRL.getId());
        currency.setCurrencyDesc(Currency.BRL.getDescricao());
        currency.setCurrencyCode(Currency.BRL.getCodigo());

        Mockito.when(documentsDataProvider.execute(any(), any(), any(), any())).thenReturn(Arrays.asList(doc));
        Mockito.when(quotationDataProvider.execute(any())).thenReturn(Arrays.asList(quotation));
        Mockito.when(api.getCurrency()).thenReturn(Arrays.asList(currency));
       var response = getConversionDataProviderImpl.execute(documentNumber, currencyCode, startDate,  endDate);
        Assertions.assertTrue(response.get(0).getValueUSD().equals(new BigDecimal(0.20).setScale(2, RoundingMode.HALF_EVEN)));
    }

    @Test
    void test_execute_return_empty(){

        Mockito.when(documentsDataProvider.execute(any(), any(), any(), any())).thenReturn(new ArrayList<>());
        Mockito.when(quotationDataProvider.execute(any())).thenReturn(new ArrayList<>());
        Mockito.when(api.getCurrency()).thenReturn(new ArrayList<>());
        var response = getConversionDataProviderImpl.execute(documentNumber, currencyCode, startDate,  endDate);
        Assertions.assertTrue(response.isEmpty());
    }


}
