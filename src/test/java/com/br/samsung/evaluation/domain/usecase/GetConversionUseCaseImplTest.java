package com.br.samsung.evaluation.domain.usecase;

import com.br.samsung.evaluation.domain.dataprovider.GetConversionDataProvider;
import com.br.samsung.evaluation.domain.model.GetConversionResponseTest;
import com.br.samsung.evaluation.domain.usecase.conversion.GetConversionUseCaseImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(SpringExtension.class)
class GetConversionUseCaseImplTest {

    @Mock
    private GetConversionDataProvider dataProvider;

    @InjectMocks
    private GetConversionUseCaseImpl useCase;

    private String documentNumber = "800002014";
    private String currencyCode = "BRL";
    private String startDate = "2020-01-03";
    private String endDate = "2020-01-03";


    @Test
    public void shouldReturnSuccessConversion() {
        var getConversionResponse = GetConversionResponseTest.create();
        Mockito.when(dataProvider.execute(any(), any(), any(), any())).thenReturn(Arrays.asList(getConversionResponse));

        var response = useCase.execute(documentNumber, currencyCode, startDate, endDate);
        Assertions.assertNotNull(response);
    }

    @Test
    public void shouldReturnNotSuccessTransferById() {
        Mockito.when(dataProvider.execute(any(), any(), any(), any())).thenReturn(null);

        var response = useCase.execute(documentNumber, currencyCode, startDate, endDate);
        Assertions.assertTrue(response == null);
    }
}
