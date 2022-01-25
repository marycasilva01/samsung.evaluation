package com.br.samsung.evaluation.domain.usecase.conversion;

import com.br.samsung.evaluation.common.exceptions.BadRequestException;
import com.br.samsung.evaluation.domain.dataprovider.GetConversionDataProvider;
import com.br.samsung.evaluation.domain.dataprovider.response.GetConversionResponse;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class GetConversionUseCaseImpl implements GetConversionUseCase{

    private final GetConversionDataProvider getConversionDataProvider;

    public List<GetConversionResponse> execute(String documentNumber, String currencyCode,
                                               String startDate, String endDate){

        checkIfRequestValid(documentNumber, currencyCode, startDate, endDate);

        return getConversionDataProvider.execute(documentNumber, currencyCode,
                formatDate(startDate),  formatDate(endDate));
    }

    private LocalDate formatDate(String date){
        var format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        if(Objects.nonNull(date)){
            return LocalDate.parse(date, format);
        }
        return null;
    }
    
    private void checkIfRequestValid(String documentNumber, String currencyCode,
                             String startDate, String endDate){
        if(isNullOrIsBlank(documentNumber) && isNullOrIsBlank(currencyCode) &&
                isNullOrIsBlank(startDate) && isNullOrIsBlank(endDate)){
            throw new BadRequestException("Request is invalid.");
        }
    }

    private boolean isNullOrIsBlank(String value){
        return Strings.isBlank(value) || Strings.isEmpty(value);

    }
}
